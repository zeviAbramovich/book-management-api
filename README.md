# Book Management API

A professional RESTful API for managing books with role-based authentication and comprehensive documentation, built with Spring Boot and containerized with Docker.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/API%20Docs-Swagger-green.svg)](http://localhost:8080/swagger-ui.html)

## 🚀 Quick Start

### Prerequisites
- **Docker** and **Docker Compose** installed
- **Java 17** (for local development)

### Run with Docker (Recommended)
```bash
# Clone the repository
# HTTPS (recommended for public repos)
git clone https://github.com/yourusername/book-management-api.git

# SSH (if you have SSH keys configured)
git clone git@github.com:yourusername/book-management-api.git

# Navigate to project directory
cd book-management-api

# Build the JAR file using Maven wrapper
./mvnw clean package

# Build and run with Docker Compose
docker-compose up --build

# Access the application
open http://localhost:8080
```

### Run Locally (Development)
```bash
# Build the application using Maven wrapper
./mvnw clean package

# Run the Spring Boot application
./mvnw spring-boot:run

# Or run the JAR directly
java -jar target/book-management-api-1.0-SNAPSHOT.jar
```

## 📋 Features

### Core Functionality
- ✅ **Complete CRUD operations** for books
- ✅ **Input validation** with custom error messages
- ✅ **Role-based authentication** (USER/ADMIN)
- ✅ **Method-level security** with annotations
- ✅ **Global exception handling** with proper HTTP status codes
- ✅ **RESTful API design** following best practices

### Security Features
- ✅ **In-memory user authentication** with encrypted passwords
- ✅ **Role-based access control** with @PreAuthorize annotations
- ✅ **Fine-grained permissions** (USER: read/write, ADMIN: full access)
- ✅ **Secure endpoints** with method-level security
- ✅ **Public development endpoints** (Swagger, H2 Console, Health checks)

### Technical Features
- ✅ **Docker containerization** for easy deployment
- ✅ **Interactive API documentation** with Swagger UI
- ✅ **H2 in-memory database** for development
- ✅ **Layered architecture** (Controller → Service → Repository)
- ✅ **Bean validation** with Jakarta Validation
- ✅ **Health check endpoints** for monitoring

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| **Framework** | Spring Boot | 3.3.5 |
| **Language** | Java | 17 |
| **Database** | H2 (in-memory) | Runtime |
| **Security** | Spring Security | Included |
| **Documentation** | SpringDoc OpenAPI | 2.0.4 |
| **Build Tool** | Maven | 3.6+ (wrapper included) |
| **Containerization** | Docker | Latest |

## 🔐 Authentication & Authorization

### User Accounts
The application includes two pre-configured users with different permission levels:

| User | Username | Password | Roles | Permissions |
|------|----------|----------|-------|-------------|
| **Regular User** | `user` | `password` | USER | Read books, Create books, Update books |
| **Administrator** | `admin` | `password` | USER, ADMIN | Full access (including delete operations) |

### Security Implementation
- **Authentication**: HTTP Basic Authentication with BCrypt password encoding
- **Authorization**: Method-level security using `@PreAuthorize` annotations
- **Session Management**: Stateful sessions for web-based access
- **Password Encryption**: BCrypt with secure salt generation

### Permission Matrix
| Operation | Endpoint | USER Role | ADMIN Role |
|-----------|----------|-----------|------------|
| **View Books** | `GET /api/v1/books` | ✅ | ✅ |
| **View Single Book** | `GET /api/v1/books/{id}` | ✅ | ✅ |
| **Create Book** | `POST /api/v1/books` | ✅ | ✅ |
| **Update Book** | `PUT /api/v1/books/{id}` | ✅ | ✅ |
| **Delete Book** | `DELETE /api/v1/books/{id}` | ❌ | ✅ |

## 📖 API Documentation

### Endpoints

#### Books API
| Method | Endpoint | Description | Required Role |
|--------|----------|-------------|---------------|
| `GET` | `/api/v1/books` | Get all books | USER, ADMIN |
| `GET` | `/api/v1/books/{id}` | Get book by ID | USER, ADMIN |
| `POST` | `/api/v1/books` | Create new book | USER, ADMIN |
| `PUT` | `/api/v1/books/{id}` | Update existing book | USER, ADMIN |
| `DELETE` | `/api/v1/books/{id}` | Delete book | ADMIN only |

#### Development Endpoints (No Authentication Required)
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/swagger-ui.html` | Interactive API documentation |
| `GET` | `/v3/api-docs` | OpenAPI specification |
| `GET` | `/h2-console` | Database console |
| `GET` | `/actuator/health` | Application health check |

## 🐳 Docker Setup

### Project Structure
```
book-management-api/
├── Dockerfile                 # Container configuration
├── docker-compose.yml         # Multi-service orchestration
├── .dockerignore              # Docker build exclusions
├── mvnw                       # Maven wrapper (Linux/Mac)
├── mvnw.cmd                   # Maven wrapper (Windows)
├── .mvn/                      # Maven wrapper configuration
├── src/                       # Application source code
├── target/                    # Build artifacts
└── README.md                  # This file
```

### Dockerfile Details
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/book-management-api-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
```

### Docker Commands
```bash
# Build JAR using Maven wrapper
./mvnw clean package -DskipTests

# Build image manually
docker build -t book-management-api .

# Run container
docker run -p 8080:8080 book-management-api

# Use Docker Compose (Recommended)
docker-compose up --build    # Build and run
docker-compose up -d         # Run in background
docker-compose down          # Stop and remove containers
docker-compose logs -f       # View logs
```

## 🔧 Development Setup

### Local Development
```bash
# Install dependencies
./mvnw clean install

# Run in development mode
./mvnw spring-boot:run

# Run tests
./mvnw test

# Package application
./mvnw clean package
```

### Database Access
- **H2 Console**: http://localhost:8080/h2-console
  - **JDBC URL**: `jdbc:h2:mem:booksdb`
  - **Username**: `sa`
  - **Password**: (empty)

### Configuration Profiles
| Profile | Description | Usage |
|---------|-------------|-------|
| `default` | Local development | `./mvnw spring-boot:run` |
| `dev` | Development with detailed logging | `SPRING_PROFILES_ACTIVE=dev` |

## 📊 API Testing

### Using Swagger UI (Recommended)
1. Start the application
2. Open http://localhost:8080/swagger-ui.html
3. Click "Authorize" and enter credentials:
  - **Username**: `admin` **Password**: `password` (for full access)
  - **Username**: `user` **Password**: `password` (for read/write access)
4. Test endpoints interactively

### Using curl

#### Authentication Required Examples
```bash
# Get all books (USER or ADMIN)
curl -u user:password http://localhost:8080/api/v1/books
curl -u admin:password http://localhost:8080/api/v1/books

# Create a new book (USER or ADMIN)
curl -u user:password -X POST http://localhost:8080/api/v1/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "publishedYear": 2008
  }'

# Update existing book (USER or ADMIN)
curl -u user:password -X PUT http://localhost:8080/api/v1/books/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Clean Code - Updated",
    "author": "Robert C. Martin",
    "publishedYear": 2008
  }'

# Delete book (ADMIN only - will fail with user credentials)
curl -u user:password -X DELETE http://localhost:8080/api/v1/books/1
# Expected: 403 Forbidden

curl -u admin:password -X DELETE http://localhost:8080/api/v1/books/1
# Expected: 204 No Content
```

#### Public Endpoints (No Authentication)
```bash
# Health check
curl http://localhost:8080/actuator/health

# API documentation
curl http://localhost:8080/v3/api-docs
```

### Sample Book Object
```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "publishedYear": 2017
}
```

## 🏗️ Architecture

### Layered Architecture with Security
```
┌─────────────────┐
│   Controller    │ ← REST endpoints, @PreAuthorize annotations
├─────────────────┤
│    Service      │ ← Business logic, additional security checks
├─────────────────┤
│   Repository    │ ← Data access layer
├─────────────────┤
│     Model       │ ← JPA entities
└─────────────────┘
```

### Security Architecture
```
┌─────────────────┐
│  Authentication │ ← Basic Auth, User Details Service
├─────────────────┤
│  Authorization  │ ← @PreAuthorize, Role-based access
├─────────────────┤
│   Controllers   │ ← Method-level security annotations
├─────────────────┤
│    Services     │ ← Business logic with security context
└─────────────────┘
```

### Package Structure
```
src/main/java/com/bookmanagement/
├── config/              # Configuration classes
│   ├── SecurityConfig.java      # Security configuration with @EnableMethodSecurity
│   └── SwaggerConfig.java       # API documentation configuration
├── controller/          # REST controllers with @PreAuthorize
│   └── BookController.java      # Role-based endpoint security
├── dto/                 # Data transfer objects
│   └── ErrorResponse.java       # Standardized error responses
├── exception/           # Exception handling
│   └── GlobalExceptionHandler.java  # Global error handling
├── model/               # JPA entities
│   └── Book.java               # Book entity with validation
├── repository/          # Data access
│   └── BookRepository.java     # JPA repository interface
├── service/             # Business logic
│   ├── BookService.java        # Service interface
│   └── BookServiceImpl.java    # Service implementation
└── BookManagementApiApplication.java  # Main application class
```

## 🔍 Security Implementation Details

### Method-Level Security
The application uses Spring Security's method-level security with `@PreAuthorize` annotations:

```java
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public List<Book> getAllBooks() { ... }

@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Void> deleteBook(@PathVariable Long id) { ... }
```

### Password Security
- **Encryption**: BCrypt with automatic salt generation
- **Storage**: In-memory user details service for demo purposes
- **Validation**: Strong password encoding prevents rainbow table attacks

### Session Management
- **Type**: Stateful sessions for web-based access
- **Timeout**: Configurable session timeout
- **Security**: CSRF protection disabled for API endpoints

## 🔍 Validation Rules

### Book Entity Validation
| Field | Rules | Example | Error Message |
|-------|-------|---------|---------------|
| **title** | Not blank, not null | "Clean Code" | "Title must not be blank" |
| **author** | Not blank, not null | "Robert C. Martin" | "Author must not be blank" |
| **publishedYear** | Not null, ≥ 1500, ≤ current year | 2008 | "Published year must be between 1500 and current year" |

### Security Validation
| Action | Validation | Response |
|--------|------------|----------|
| **Unauthorized Access** | No credentials provided | 401 Unauthorized |
| **Forbidden Action** | Insufficient permissions | 403 Forbidden |
| **Invalid Credentials** | Wrong username/password | 401 Unauthorized |

### Error Response Format
```json
{
  "timestamp": "2025-07-01T15:30:45",
  "status": 400,
  "error": "Validation Failed",
  "message": "Input validation failed",
  "validationErrors": {
    "title": "Title must not be blank",
    "publishedYear": "Published year must be no earlier than 1500"
  }
}
```

## 🚦 Health Monitoring

### Health Check
```bash
# Check application status
curl http://localhost:8080/actuator/health

# Response
{
  "status": "UP"
}
```

### Available Actuator Endpoints
- `/actuator/health` - Application health status
- `/actuator/info` - Application information

## 🧪 Testing Security

### Test Different User Roles
```bash
# Test USER role access
curl -u user:password http://localhost:8080/api/v1/books
curl -u user:password -X DELETE http://localhost:8080/api/v1/books/1  # Should fail

# Test ADMIN role access
curl -u admin:password http://localhost:8080/api/v1/books
curl -u admin:password -X DELETE http://localhost:8080/api/v1/books/1  # Should succeed

# Test unauthenticated access
curl http://localhost:8080/api/v1/books  # Should require authentication
```

### Expected Security Responses
| Scenario | Expected Status | Response Body |
|----------|----------------|---------------|
| **Valid USER credentials** | 200 OK | Book data |
| **Valid ADMIN credentials** | 200 OK | Book data |
| **No credentials** | 401 Unauthorized | Authentication required |
| **Invalid credentials** | 401 Unauthorized | Bad credentials |
| **USER tries DELETE** | 403 Forbidden | Access denied |
| **ADMIN tries DELETE** | 204 No Content | Success |

### Code Standards
- **Java 17** features and syntax
- **Spring Boot** best practices
- **RESTful** API design principles
- **Method-level security** with annotations
- **Comprehensive validation** and error handling
- **Docker-first** deployment approach

## 📝 Notes for Reviewers

### Technical Decisions
- **H2 Database**: Chosen for simplicity and zero-configuration setup
- **Basic Authentication**: Suitable for demo purposes, easily upgradeable to JWT
- **Method-Level Security**: Scalable approach using @PreAuthorize annotations
- **In-Memory Users**: Perfect for demo, easily replaceable with database/LDAP
- **Docker**: Ensures consistent environment across different systems
- **Swagger**: Provides immediate API testing capability
- **Maven Wrapper**: Ensures consistent build environment without local Maven installation

### Security Design Choices
- **@EnableMethodSecurity**: Modern Spring Security approach for fine-grained permissions
- **@PreAuthorize**: Declarative security at method level for better maintainability
- **Role-based Access Control**: Clear separation between USER and ADMIN capabilities
- **BCrypt Encryption**: Industry-standard password hashing
- **Stateful Sessions**: Appropriate for demo environment

### Production Considerations
- Replace H2 with PostgreSQL/MySQL for production
- Implement JWT authentication for stateless API
- Add user registration and password reset functionality
- Implement audit logging for security events
- Add rate limiting and API versioning
- Include comprehensive test coverage with security tests
- Set up CI/CD pipeline with security scanning

### Demo Highlights
- **Zero Prerequisites**: Only Docker required, Maven wrapper included
- **Immediate Setup**: `./mvnw clean package -DskipTests && docker-compose up --build`
- **Interactive Testing**: Swagger UI with built-in authentication
- **Role-based Demo**: Easy to demonstrate USER vs ADMIN capabilities
- **Professional Structure**: Clean architecture with proper security implementation
- **Scalable Security**: Method-level annotations ready for enterprise growth

### Security Features Demonstrated
- **Authentication**: Multiple user accounts with different roles
- **Authorization**: Fine-grained permissions using annotations
- **Input Validation**: Comprehensive validation with custom error messages
- **Error Handling**: Proper HTTP status codes and security-aware responses
- **Documentation**: Security requirements clearly documented in Swagger

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---