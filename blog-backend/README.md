# 🖥️ Blog Platform - Backend

Spring Boot REST API for the Blog Platform application.

## 📋 Overview

This is the backend service for the Blog Platform, built with Spring Boot. It provides a RESTful API for managing blog posts, categories, tags, and user authentication.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Security** - Authentication & Authorization
- **Spring Data JPA** - Database access
- **PostgreSQL** - Primary database
- **JWT** - JSON Web Tokens for authentication
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management

## 🏗️ Project Structure

```
blog-backend/
├── src/
│   ├── main/
│   │   ├── java/com/kte/blog/
│   │   │   ├── controllers/        # REST controllers
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── PostController.java
│   │   │   │   ├── CategoryController.java
│   │   │   │   ├── TagController.java
│   │   │   │   └── ErrorController.java
│   │   │   ├── services/           # Business logic
│   │   │   │   ├── impl/
│   │   │   │   │   ├── PostServiceImpl.java
│   │   │   │   │   ├── AuthenticationServiceImpl.java
│   │   │   │   │   ├── CategoryServiceImpl.java
│   │   │   │   │   ├── TagServiceImpl.java
│   │   │   │   │   └── UserServiceImpl.java
│   │   │   ├── repositories/       # Data access layer
│   │   │   ├── domain/            # Domain models
│   │   │   │   ├── entities/
│   │   │   │   ├── dtos/
│   │   │   │   ├── request/
│   │   │   │   ├── response/
│   │   │   │   └── enumerations/
│   │   │   ├── security/          # Security configuration
│   │   │   │   └── BlogUserDetails.java
│   │   │   ├── mappers/           # Entity-DTO mappers
│   │   │   └── BlogApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-dev.properties
│   └── test/                      # Unit and integration tests
├── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+
- PostgreSQL 14+

### Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE blogdb;
```

2. Create a user (optional):
```sql
CREATE USER bloguser WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE blogdb TO bloguser;
```

### Configuration

Create or update `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.secret=your-secret-key-at-least-256-bits-long-for-hs256-algorithm

# Server Configuration
server.port=8080

# CORS Configuration
cors.allowed-origins=http://localhost:5173
```

### Running the Application

#### Development Mode

```bash
# Using Maven
mvn spring-boot:run

# Or with Maven Wrapper
./mvnw spring-boot:run
```

#### Production Mode

```bash
# Build the JAR
mvn clean package

# Run the JAR
java -jar target/blog-backend-0.0.1-SNAPSHOT.jar
```

The API will be available at `http://localhost:8080`

## 📡 API Endpoints

### Authentication

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/v1/auth/login` | Login user | No |

### Posts

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/posts` | Get all published posts | No |
| GET | `/api/v1/posts/{id}` | Get post by ID | No |
| GET | `/api/v1/posts/drafts` | Get user's draft posts | Yes |
| POST | `/api/v1/posts` | Create new post | Yes |
| PUT | `/api/v1/posts/{id}` | Update post | Yes |
| DELETE | `/api/v1/posts/{id}` | Delete post | Yes |

### Categories

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/categories` | Get all categories | No |
| POST | `/api/v1/categories` | Create category | Yes |
| DELETE | `/api/v1/categories/{id}` | Delete category | Yes |

### Tags

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/v1/tags` | Get all tags | No |
| POST | `/api/v1/tags` | Create tag | Yes |
| DELETE | `/api/v1/tags/{id}` | Delete tag | Yes |

## 🔒 Security

### JWT Authentication

The API uses JWT (JSON Web Tokens) for authentication:

1. **Login**: Send credentials to `/api/v1/auth/login`
2. **Receive Token**: Get JWT token in response
3. **Use Token**: Include token in `Authorization` header for protected endpoints

```
Authorization: Bearer <your-jwt-token>
```

### Token Details

- **Expiration**: 24 hours (86400000 ms)
- **Algorithm**: HS256
- **Claims**: Subject (username/email)

## 🎯 Key Features

### Post Management

- **CRUD Operations**: Create, Read, Update, Delete posts
- **Status Management**: DRAFT and PUBLISHED states
- **Reading Time**: Automatic calculation based on word count (200 words/minute)
- **Filtering**: Filter by category and/or tag
- **Draft System**: Users can save posts as drafts

### Category & Tag System

- **Organization**: Posts can be categorized and tagged
- **Filtering**: Filter posts by category or tag
- **Relationship Management**: Many-to-many relationship with posts

### Error Handling

Global exception handling with custom error responses:
- `EntityNotFoundException` → 404
- `BadCredentialsException` → 401
- `IllegalArgumentException` → 400
- `IllegalStateException` → 409

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=PostServiceImplTest

# Run with coverage
mvn test jacoco:report
```

## 🏗️ Building

```bash
# Clean and build
mvn clean install

# Skip tests
mvn clean install -DskipTests

# Create executable JAR
mvn clean package
```

## 🐳 Docker (Optional)

Create a `Dockerfile`:

```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/blog-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:

```bash
docker build -t blog-backend .
docker run -p 8080:8080 blog-backend
```

## 📊 Database Schema

### Main Tables

- **users**: User accounts
- **posts**: Blog posts
- **categories**: Post categories
- **tags**: Post tags
- **post_tags**: Many-to-many relationship table

## 🔧 Development Tips

### Hot Reload

Spring Boot DevTools is included for automatic restart during development.

### Database Migrations

Consider using Flyway or Liquibase for production database migrations:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

### Logging

Configure logging in `application.properties`:

```properties
logging.level.com.kte.blog=DEBUG
logging.level.org.springframework.web=DEBUG
```

## 🤝 Contributing

Please follow these guidelines:

1. Follow Java coding conventions
2. Write unit tests for new features
3. Update API documentation
4. Use meaningful commit messages

## 📝 Notes

- WebFlux is explicitly excluded in the main application
- JWT secret should be at least 256 bits for HS256
- Database schema is auto-updated (change to `validate` in production)

---

Made with ❤️ using Spring Boot