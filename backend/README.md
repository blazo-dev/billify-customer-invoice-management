# Billify Backend

This is the backend of **Billify**, a full-stack business application for managing customers and invoices. The backend is built with **Java 17** and **Spring Boot**, using **MySQL** as the relational database.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **JWT Authentication**
- **Maven**

## 📁 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/dev/blazo/billify/        # Application code
│   │   └── resources/                     # Application properties, templates, static files
│   └── test/                              # Unit and integration tests
└── pom.xml                                # Maven build config
```

## ⚙️ Configuration

Make sure to update `application.yml` (or `application.properties`) with your database credentials:

```yaml
spring:
  application:
    name: billify
  datasource:
    url: jdbc:mysql://localhost:3306/billify_db
    username: username_example
    password: password_example
  jpa:
    generate-ddl: true
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        globally_quoted_identifiers: true
        format_sql: true
  sql:
    init:
      mode: never
```

## ▶️ Run the App

To start the backend locally:

```bash
./mvnw spring-boot:run
```

Or if using Windows:

```bash
mvnw.cmd spring-boot:run
```

## ✅ API Overview

> _Coming soon..._

## 🧪 Testing

To run unit tests:

```bash
./mvnw test
```

## 📄 License

This backend code is part of the Billify project by [@blazo-dev](https://github.com/blazo-dev).