# Projet API

## Présentation de l'équipe
- BAELDEN Tom
- PLAETEVOET Gaëlle

## Organisation
Les tâches sont suivies sur notre GitHub Board et le travail est partagé par commits réguliers sur ce dépôt.

## Explication du projet
API de carnet d'aventure sur le thème de The Legend of Zelda, réalisée avec Spring Boot dans le cadre du cours R3-03 (BUT2). Un utilisateur crée des aventures et y consigne les éléments de Hyrule rencontrées (monstres, matériaux, équipements, ...), dont les données proviennent de l'API externe Hyrule Compendium.

### Etat d'avancement
| Fonctionnalité | Etat |
| --- | --- |
| CRUD complet sur `Adventure` | Terminé |
| Validation des données et gestion du 404 | Terminé |
| Tests de l'API avec Bruno | Terminé |
| Import de Hyrule Compendium en base | Terminé |
| Relation One-to-Many entre `Adventure` et ses entrées | Terminé |
| Authentification par Bearer token | Terminé |

### API externe
Les données sur le monde de Zelda viennent de l'API évoquée plus tôt, publiée sous licence MIT. Elle couvre les jeux *Breath Of The Wild* et *Tears Of The Kingdom*.
Cette API est hébergée gratuitement et peut être lente/indisponible. Pour ne pas en dépendre à chaque requête, les données sont copiées dans notre base par l'endpoint POST /compendium/import.

## Technologies du projet
- Java 21, Spring Boot, Spring Data JPA / Hibernate
- PostgreSQL 16 (BDD)
- Tables créées automatiquement par Hibernate (`ddl-auto: update`), sans perte de données entre 2 redémarrages.
- Docker et Docker Compose
- Maven, Lombok, Bean Validation
- API externe: [Hyrule Compendium API](https://gadhagod.github.io/Hyrule-Compendium-API) (Licence MIT)
- Bruno (tests de l'API), GitHub Projects (suivi des tâches)

### Architecture
Code organisé en couches:
- `controller` reçoit les requêtes HTTP et renvoie les réponses
- `service` contient la logique métier
- `repository` accède à la BDD
- `entity` représente la table en base
- `dto` objet reçu dans les requêtes, avec validation

### Modèle de données
Une aventure contient plusieurs pages (relation **One-to-Many**). Chaque page est liée à un élément du compendium.

**SCHEMA MERMAID A IMPORTER**

La suppression d'une aventure supprimer aussi ses pages (cascade).

### Lancer l'application
Prérequis: Docker et Docker Compose
```bash
cp .env.example .env
echo "JWT_SECRET=$(openssl rand -hex 32)" > .env
docker compose up -d --build
```
Le fichier `.env` contient la clé qui signe les jetons: il n'est pas versionné et ne doit jamais être publié.
L'API est disponible sur `http://localhost:8084`. Adminer (interface de la base) accessible sur `http://localhost:8081`.

## Documentation de l'API
Base URL: `http://localhost:8084`

| Méthode | Route | Description | Jeton | Succès | Erreurs |
| --- | --- | --- | --- | --- |
| POST | `/adventures` | Créer une aventure | | 201 | 400 |
| GET | `/adventures` | Lister toutes les aventures | | 200 | |
| GET | `/adventures/{id}` | Récupérer une aventure | | 200 | 404 |
| GET | `/adventures/{id}` | Modifier une aventure | | 200 | 400, 404 |
| DELETE | `/adventures/{id}` | Supprimer une aventure | | 204 | 404 |
| POST | `compendium/import` | Importer le Hyrule Compendium en base | | 200 | 502 (API externe indisponible) |
| GET | `/compendium/{id}` | Lister les éléments du compendium | | 200 | |
| GET | `/compendium/{id}` | Récupérer un élément du compendium | | 200 | 404 |
| POST | `/adventures/{id}/entries` | Ajouter une page à une aventure | | 201 | 400, 404 |
| GET | `/adventures/{id}/entries` | Lister les pages d'une aventure | | 200 | 404 |
| PUT | `/adventures/{id}/entries/{entryId}` | Modifier une page ||  200 | 400, 404 |
| DELETE | `/adventures/{id}/entries/{entryId}` | Supprimer une page | | 204 | 404 |
| POST | `/auth/register` | Créer un compte | Non | 201 | 400, 409 |
| POST | `/auth/login` | Se connecter (renvoie un jeton) | Non | 200 | 400, 401 |
| GET | `/compendium`, `/compendium{id}` | Lire le compendium | Non | 200 | 404 |
| POST | `/compendium/import` | Importer le compendium | Oui | 200 | 401, 502 |
Les routes protégées demandent l'en-tête `Authorization: Bearer <jeton>`.
Remarque: un jeton absent, invalide ou expiré renverra **401**.

### Tests
Les requêtes de test sont dans le dossier `API_R303` : à ouvrir avec Bruno.

## Recherches - Authentification et jeron Bearer
### Pourquoi sécuriser l'API?
Pour le moment, quiconcque peut créer, modifier ou supprimer des données. Il faut:
- **S'authentifier**: prouver qui on est
- **Etre autorisé**: avoir le droit de faire ce qu'on fait
Quand une requête échoue, l'API le dit avec:
- 401 Unauthorized
- 403 Forbidden

### Jeton Bearer
Un code que l'on reçoit après s'être connecté, et qu'on envoie ensuite avec chaque requête. Le déteneur du jeton est considéré comme "autorisé".
Ce jeton est envoyé dans un en-tête HTTP:
```
Authorization: Bearer <le jeton>
```
Le jeton donne accès à l'API, il faut donc le protéger. On le transmet en HTTPS, on ne le publie jamais et on lui donne une durée de vie limitée pour limiter les dégâts en cas de vol.

### JWT (JSON Web Token)
Format de jeton le plus courant. Il est composé de 3 parties:
```
en-tête . contenu . signature
```
- L'en-tête indique la méthode de signature.
- Le contenu porte des informations: qui est l'utiliateur, quand le jeton expire.
- La signature: elle est calculée avec une clé secrète que le serveur connaît. Si quelqu'un modifie le jeton, la signature ne correspond plus et l'API le refuse.

**Attention**: Un JWT est signé mais pas chiffré. Quiconcque peut lire son contenu.

**Avantage**: L'API n'a pas besoin de garder de garder en mémoire les utilisateurs connectés: il suffit de vérifier la signature.

### OAuth 2.0
Standard permettant à une application d'accéder à des informations d'un utilisateur sans connaître son mot de passe.
4 acteurs interviennent:
- Propriétaire de la ressource
- Client
- Serveur d'autorisation
- Serveur de ressources

Les 3 notions vues se combinent.

### Notre choix:
Version simple avec Spring Security:
- Route d'inscription et route de connexion, renvoyant un JWT.
- Routes d'écriture réservées aux utiliateurs envoyant un jeton Bearer valide.
- Code 401 quand le jeton est absent ou invalide.