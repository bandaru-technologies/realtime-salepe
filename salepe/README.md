# Salepe

Spring Boot 4.1 backend (`src/`) with an Expo / React Native UI (`salepe-ui/`).

## Stack

- Java 17, Maven wrapper (`./mvnw`)
- Spring Web MVC, Security (JWT), Data JPA, Kafka, Mail, Actuator
- PostgreSQL 16, Apache Kafka 3.8
- Expo (React Native, web supported)

## Prerequisites

- **JDK 17 or 21.** JDK 27 fails to compile because Lombok is incompatible with it.
  Set `JAVA_HOME` accordingly.
- Node.js and npm for the UI
- PostgreSQL and Kafka running locally (Docker is fine)

Defaults in `src/main/resources/application.yml`: Postgres at
`localhost:5432` (user and password `postgres`), Kafka at `localhost:9092`.

## Run the backend

```bash
export JAVA_HOME=/path/to/jdk17
./mvnw spring-boot:run
```

Override settings with environment variables when ports clash or Postgres
runs elsewhere, for example:

```bash
SERVER_PORT=8081 \
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5433/postgres \
./mvnw spring-boot:run
```

Check it is up: `curl localhost:8081/actuator/health` → `{"status":"UP"}`.

## Run the UI

```bash
cd salepe-ui
npm install
npx expo start --web --port 8090   # or: npm run web / npm run ios
```

Open http://localhost:8090. Pick any free port if 8090 is taken.

## API

| Method | Path                 | Description       |
|--------|----------------------|-------------------|
| POST   | `/api/auth/register` | Register a user   |
| POST   | `/api/auth/login`    | Log in, returns JWT |
| GET    | `/api/users/{id}`    | Get a user        |

A Postman collection is in `postman/`.

## Notes

- Mail credentials in `application.yml` should be moved to environment
  variables or a secrets store before sharing the repo.
