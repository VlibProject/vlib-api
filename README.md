# Vlib - Vélib API

![Java](https://img.shields.io/badge/Java-25-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-6DB33F?logo=spring-boot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3-C71A36?logo=apache-maven&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-336791?logo=postgresql&logoColor=white)

A robust REST API for accessing and managing Vélib (Paris bike-sharing system) data with user authentication, real-time station information, and comprehensive search capabilities.

## 🎯 Overview

Vlib is a Spring Boot application that provides a comprehensive REST API for querying and managing Vélib bike-sharing station data in Paris. It includes secure JWT-based authentication, real-time data synchronization, and advanced search capabilities to filter stations by various criteria.

## 🛠 Tech Stack

### Core Framework
- **Java 25**: Latest JDK version
- **Spring Boot 3.5.7**: Application framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: ORM and database access

### Database & Persistence
- **PostgreSQL 14+**: Primary database
- **Flyway**: Database migration tool

### Additional Libraries
- **JWT (JJWT)**: Token generation and validation
- **WebFlux**: Reactive web client for external API calls
- **Springdoc OpenAPI**: API documentation generation
- **Maven**: Build and dependency management

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 25+**: [Download JDK](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+**: [Download Maven](https://maven.apache.org/download.cgi)
- **PostgreSQL 14+**: [Download PostgreSQL](https://www.postgresql.org/download/)
- **Git**: [Download Git](https://git-scm.com/download)

### Environment Variables
- `JAVA_HOME`: Points to your Java installation directory
- `MAVEN_HOME`: Points to your Maven installation directory

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/VlibProject/vlib-api.git
cd vlib
```

### 2. Create Database

```bash
# Using PostgreSQL CLI
createdb vlib

# Or using psql
psql -c "CREATE DATABASE vlib;"
```

### 3. Install Dependencies

```bash
# Using Maven wrapper (recommended)
./mvnw clean install

# Or using system Maven
mvn clean install
```

## ⚙️ Configuration

### Application Properties

Create an `application.properties` file or `application.yml` in `src/main/resources/`:

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api/v1

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/vlib
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

# Flyway Configuration
spring.flyway.locations=classpath:db/migration
spring.flyway.enabled=true

# JWT Configuration
jwt.secret=your-secret-key-minimum-256-bits-long-for-security
jwt.expiration=86400000

# Actuator Configuration
management.endpoints.web.exposure.include=health,metrics,info
management.endpoint.health.show-details=when-authorized

# Logging
logging.level.root=INFO
logging.level.fr.host_dcode.vlib=DEBUG
```

### JWT Configuration

Generate a secure secret key:

```bash
# Generate a 256-bit (32 bytes) base64-encoded secret
openssl rand -base64 32
```

Update your `application.properties` with the generated key.

## 📖 Usage

### Starting the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Using system Maven
mvn spring-boot:run

# Or build and run JAR
./mvnw clean package
java -jar target/vlib-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

### Authentication

#### Login Endpoint
**POST** `/api/v1/auth/`

Request body:
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "expiration": 86400000
}
```

#### Using the Token
Include the JWT token in the `Authorization` header for protected endpoints:

```bash
curl -H "Authorization: Bearer <token>" http://localhost:8080/api/v1/station/
```

### API Endpoints

#### Stations

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/station/` | Get all stations (paginated) | Yes |
| GET | `/api/v1/station/search/` | Search stations by criteria | Yes |

#### Authentication

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/v1/auth/` | Authenticate user and get JWT token | No |

#### Monitoring

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/actuator/health` | Application health status | No |
| GET | `/actuator/metrics` | Application metrics | No |

### Examples

#### Get All Stations (Page 0, 10 items)

```bash
curl -H "Authorization: Bearer <token>" \
  "http://localhost:8080/api/v1/station/?page=0&size=10&sort=name,asc"
```

Response:
```json
{
  "content": [
    {
      "id": "1",
      "name": "Station A",
      "city": "Paris",
      "stationCode": "ST001",
      "bikeCount": 15,
      "bikeStandCount": 5
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "totalElements": 150,
    "totalPages": 15
  }
}
```

#### Search Stations

```bash
curl -H "Authorization: Bearer <token>" \
  "http://localhost:8080/api/v1/station/search/?name=Chatelet&city=Paris&page=0&size=20"
```

#### Authenticate

```bash
curl -X POST "http://localhost:8080/api/v1/auth/" \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'
```

## 📁 Migrations

Database schema is managed using Flyway. Migration files are located in `src/main/resources/db/migration/`. Migrations are automatically applied on application startup.

## 💻 Development

### Build the Project

```bash
./mvnw clean package
```

### Run in Development Mode

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Code Style

The project follows standard Java conventions. Ensure code is properly formatted and documented.

### Hot Reload

Enable Spring DevTools for automatic restart on code changes:

```properties
spring.devtools.restart.enabled=true
```

### Create New Endpoints

1. Create a method in the appropriate `Service` class
2. Create a corresponding method in the `Controller` class
3. Add necessary annotations (`@GetMapping`, `@PostMapping`, etc.)
4. Test the endpoint using Swagger or curl

## 📚 API Documentation

Interactive API documentation is available via Swagger UI once the application is running:

```
http://localhost:8080/swagger-ui.html
```

OpenAPI specification (JSON):
```
http://localhost:8080/v3/api-docs
```