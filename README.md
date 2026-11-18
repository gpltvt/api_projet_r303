# Projet API

## Présentation de l'équipe
- BAELDEN Tom
- PLAETEVOET Gaëlle

## Organisation
Les tâches sont suivies sur notre GitHub Board et le travail est partagé par commits réguliers sur ce dépôt.

## Explication du projet
API de carnet d'aventure sur le thème de The Legend of Zelda, réalisée avec Spring Boot dans le cadre du cours R3-03 (BUT2). Un utilisateur crée des aventures et y consigne les éléments de Hyrule rencontrées (monstres, matériaux, équipements, ...), dont les données proviennent de l'API externe Hyrule Compendium.

## Technologies du projet
- Java 21, Spring Boot, Spring Data JPA / Hibernate
- PostgreSQL 16 (BDD)
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

### Tests
Les requêtes de test sont dans le dossier `API_R303` : à ouvrir avec Bruno.