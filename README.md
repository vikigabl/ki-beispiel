# Ki-Beispiel

Java Spring Boot Project with REST-API, JPA and MySQL

## Requirements

- **JDK Version**: 25
- **Spring Boot**: 3.4.5
- **Database**: MySQL
- **ORM**: JPA/Hibernate
- **Frontend**: Thymeleaf with HTML/CSS/JavaScript

## Features

- REST-API for CRUD operations
- GUI Frontend for item management
- MySQL database integration
- JPA entity management

## Getting Started

### Prerequisites

1. Install JDK 25
2. Install MySQL Server
3. Create a database named `kibeispiel` (or configure it in `application.properties`)

### Configuration

Edit `src/main/resources/application.properties` to configure your MySQL connection:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kibeispiel
spring.datasource.username=your-username
spring.datasource.password=your-password
```

### Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Access the Application

- **GUI Frontend**: http://localhost:8080/
- **REST-API**: http://localhost:8080/api/items

## REST-API Endpoints

| Method | Endpoint           | Description         |
|--------|-------------------|---------------------|
| GET    | /api/items        | Get all items       |
| GET    | /api/items/{id}   | Get item by ID      |
| POST   | /api/items        | Create new item     |
| PUT    | /api/items/{id}   | Update item         |
| DELETE | /api/items/{id}   | Delete item         |

## Project Structure

```
src/
├── main/
│   ├── java/com/example/kibeispiel/
│   │   ├── KiBeispielApplication.java     # Main application class
│   │   ├── controller/
│   │   │   ├── ItemController.java        # REST-API controller
│   │   │   └── WebController.java         # GUI Frontend controller
│   │   ├── entity/
│   │   │   └── Item.java                  # JPA Entity
│   │   ├── repository/
│   │   │   └── ItemRepository.java        # JPA Repository
│   │   └── service/
│   │       └── ItemService.java           # Business logic
│   └── resources/
│       ├── application.properties         # Application configuration
│       ├── templates/
│       │   └── index.html                 # Thymeleaf template
│       └── static/
│           ├── css/style.css              # Stylesheet
│           └── js/app.js                  # JavaScript
└── test/
    └── java/com/example/kibeispiel/
        └── controller/
            └── ItemControllerTests.java   # API tests
```

## Technologies Used

- **Java 25** - Programming language
- **Spring Boot 3.4.5** - Application framework
- **Spring Web** - REST-API
- **Spring Data JPA** - Database access
- **Thymeleaf** - Template engine for GUI
- **MySQL** - Database
- **H2** - In-memory database for testing
- **Maven** - Build tool
