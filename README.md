# Projet API

## Présentation de l'équipe
BAELDEN Tom
PLAETEVOET Gaëlle

## Organisation
Les tâches sont suivies sur notre GitHub Board et le travail est partagé par commits réguliers sur ce dépôt.

## Explication du projet
API de gestion de formulaires réalisée avec Spring Boot (cours R3-03 BUT2).

## Technologies du projet
- Java 21, Spring Boot, Spring Data JPA / Hibernate
- PostGreSQL 16 (BDD)
- Docker et Docker Compose
- Maven, Lombok, Bean Validation
- Bruno (tests de l'API), GitHub Projects (suivi des tâches)

### Architecture
Code organisé en couches:
`controller` reçoit les requêtes HTTP et renvoie les réponses
`service` contient la logique métier
`repository` accède à la BDD
`entity` représente la table en base
`dto` objet reçu dans les requêtes, avec validation

### Lancer l'application
Prérequis: Docker et Docker Compose
```bash
docker compose up -d --build
```
L'API est disponible sur `http//localhost:8084`. Adminer (interface de la base) accessible sur `http://localhost:8081`.

## Documentation de l'API
Base URL: `http://localhost:8084`

| Méthode | Route | Description | Succès | Erreurs |
| --- | --- | --- | --- | --- |
| POST | `/forms` | Créer un form | 201 | 400 |
| GET | `/forms` | Lister tous les forms | 200 | |
| GET | `/forms/{id}` | Récupérer un form | 200 | 404 |
| GET | `/forms/{id}` | Modifier un form | 200 | 400, 404 |
| DELETE | `/forms/{id}` | Supprimer un form | 204 | 404 |

### Tests
Les requêtes de test sont dans le dossier `API_R303` : à ouvrir avec Bruno.