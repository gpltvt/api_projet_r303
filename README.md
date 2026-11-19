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
| Relation One-to-Many entre `Adventure` et ses entrées | A faire |
| Authentification par Bearer token | A faire |

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

### Lancer l'application
Prérequis: Docker et Docker Compose
```bash
docker compose up -d --build
```
L'API est disponible sur `http://localhost:8084`. Adminer (interface de la base) accessible sur `http://localhost:8081`.

## Documentation de l'API
Base URL: `http://localhost:8084`

| Méthode | Route | Description | Succès | Erreurs |
| --- | --- | --- | --- | --- |
| POST | `/adventures` | Créer une aventure | 201 | 400 |
| GET | `/adventures` | Lister toutes les aventures | 200 | |
| GET | `/adventures/{id}` | Récupérer une aventure | 200 | 404 |
| GET | `/adventures/{id}` | Modifier une aventure | 200 | 400, 404 |
| DELETE | `/adventures/{id}` | Supprimer une aventure | 204 | 404 |
| POST | `compendium/import` | Importer le Hyrule Compendium en base | 200 | 502 (API externe indisponible) |
| GET | `/compendium/{id}` | Lister les éléments du compendium | 200 | |
| GET | `/compendium/{id}` | Récupérer un élément du compendium | 200 | 404 |

### Tests
Les requêtes de test sont dans le dossier `API_R303` : à ouvrir avec Bruno.