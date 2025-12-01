# Ki-Beispiel

Java Spring Boot Project with REST-API, JPA and MySQL

## Requirements

- **JDK Version**: 17
- **Spring Boot**: 3.4.5
- **Database**: MySQL
- **ORM**: JPA/Hibernate
- **Frontend**: Thymeleaf with HTML/CSS/JavaScript

## Features

- REST-API for CRUD operations
- GUI Frontend for item management
- MySQL database integration
- JPA entity management
- Menu database with pre-populated menus (Wienerschnitzel, Fisch)

## Getting Started

### Prerequisites

1. Install JDK 17
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
- **REST-API Items**: http://localhost:8080/api/items
- **REST-API Menus**: http://localhost:8080/api/menus

## REST-API Endpoints

### Items API

| Method | Endpoint           | Description         |
|--------|-------------------|---------------------|
| GET    | /api/items        | Get all items       |
| GET    | /api/items/{id}   | Get item by ID      |
| POST   | /api/items        | Create new item     |
| PUT    | /api/items/{id}   | Update item         |
| DELETE | /api/items/{id}   | Delete item         |

### Menus API

| Method | Endpoint           | Description         |
|--------|-------------------|---------------------|
| GET    | /api/menus        | Get all menus       |
| GET    | /api/menus/{id}   | Get menu by ID      |
| POST   | /api/menus        | Create new menu     |
| PUT    | /api/menus/{id}   | Update menu         |
| DELETE | /api/menus/{id}   | Delete menu         |

### Pre-populated Menus

The application starts with the following menus:
- **Menü 1: Wienerschnitzel** - Klassisches Wienerschnitzel mit Kartoffelsalat
- **Menü 2: Fisch** - Frischer Fisch mit Gemüse und Reis

## Project Structure

```
src/
├── main/
│   ├── java/com/example/kibeispiel/
│   │   ├── KiBeispielApplication.java     # Main application class
│   │   ├── config/
│   │   │   └── DataInitializer.java       # Data initializer for menus
│   │   ├── controller/
│   │   │   ├── ItemController.java        # REST-API controller for items
│   │   │   ├── MenuController.java        # REST-API controller for menus
│   │   │   └── WebController.java         # GUI Frontend controller
│   │   ├── entity/
│   │   │   ├── Item.java                  # JPA Entity for items
│   │   │   └── Menu.java                  # JPA Entity for menus
│   │   ├── repository/
│   │   │   ├── ItemRepository.java        # JPA Repository for items
│   │   │   └── MenuRepository.java        # JPA Repository for menus
│   │   └── service/
│   │       ├── ItemService.java           # Business logic for items
│   │       └── MenuService.java           # Business logic for menus
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
            ├── ItemControllerTests.java   # API tests for items
            └── MenuControllerTests.java   # API tests for menus
```

## Technologies Used

- **Java 17** - Programming language
- **Spring Boot 3.4.5** - Application framework
- **Spring Web** - REST-API
- **Spring Data JPA** - Database access
- **Thymeleaf** - Template engine for GUI
- **MySQL** - Database
- **H2** - In-memory database for testing
- **Maven** - Build tool
