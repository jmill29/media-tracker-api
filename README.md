# TV Tracker API – Cognixia Capstone Project ![Coverage](https://img.shields.io/badge/Coverage-80%25-brightgreen)

This is the backend API for my Cognixia Future Horizons Capstone Project. It’s a Java-based Spring Boot REST API that allows users to track their TV show watch history. This project demonstrates secure user authentication, layered architecture, robust exception handling, and thorough testing.

---

## 🎯 Project Objective

To design and build a secure, maintainable, and fully functional backend application for managing a user's personalized TV watch history using Spring Boot and MySQL. This project showcases my backend development skills, including authentication, data persistence, and RESTful API design.

---

## ✅ Key Features

- **User Authentication** via Spring Security (`JdbcUserDetailsManager`)
- **BCrypt-encrypted passwords** for secure login
- **Track watch status** (e.g., Watching, Completed, Plan to Watch) per show
- **REST API design** using Controller-Service-DAO architecture
- **Global Exception Handling** with `@ControllerAdvice`
- **Modular and Testable Codebase**
- **SQL seed script** for fast local setup
- **Swagger/OpenAPI UI** for exploring and testing endpoints
- **Javadoc HTML Documentation** for developers and maintainers
- **JaCoCo Test Coverage Report** (80%+ coverage)

---

## 📖 API Documentation

- 🔍 **Swagger/OpenAPI Docs (Live UI)**:  
  👉 [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

- 📚 **Javadoc HTML Documentation**:  
  👉 [View Javadoc HTML](./docs/index.html)

---

## 📈 Code Quality & Test Coverage

- ✅ **170+ unit/integration tests**
- 🧪 **DAO, Service, Controller, Exception, and Utility layers tested**
- 📊 **Test Coverage**: 80%+ (generated with [JaCoCo](https://www.jacoco.org/jacoco/))
- 🛡️ Tests ensure reliability, robustness, and production-readiness

---

## 📌 Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET`  | `/api/shows` | Fetch all TV shows |
| `GET`  | `/api/watch-history` | Fetch authenticated user's tracked shows |
| `POST` | `/api/watch-history` | Add a new tracked show with watch status |
| `PUT`  | `/api/watch-history` | Update watch status of a tracked show |
| `DELETE` | `/api/watch-history/{showId}` | Remove a show from watch history |

> 🔐 All `watch-history` endpoints require Basic Auth using valid user credentials

---

## 🛠 Technologies Used

- Java 17  
- Spring Boot  
- Spring Security  
- JDBC (manual implementation, no Hibernate)  
- MySQL (local instance)  
- Maven  
- Swagger (Springdoc OpenAPI)  
- Javadoc (HTML output)  
- JaCoCo (Test coverage)

---

## 🚀 How to Run Locally

1. **Clone the project**:
   ```bash
   git clone https://github.com/jmill29/tv-show-tracker-api.git
   cd tv-show-tracker-api
   ```

2. **Set up the MySQL database**:
   - Open MySQL Workbench (or another SQL client)
   - Run the schema seed script:  
     `src/main/resources/capstone_schema_seed.sql`

3. **Configure `application.properties`**:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tv_show_tracker
   spring.datasource.username=your_mysql_user
   spring.datasource.password=your_mysql_password
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

---

## 🧪 Example Usage

### `POST /api/watch-history`

**Request Body:**
```json
{
  "show_id": 3,
  "status": "Watching"
}
```

**Header:**
```
Authorization: Basic base64encoded(username:password)
```

---

## 📂 Related Links

- 🖥️ **Frontend Console App**: [tv-tracker-frontend](https://github.com/jmill29/tv-tracker-frontend)  
- 📋 **Kanban Board**: [GitHub Projects Board](https://github.com/users/jmill29/projects/1)

---

## 💡 Notes for Evaluators

- The app follows a clean layered architecture with clear separation of concerns.
- Passwords are securely hashed using BCrypt.
- The DAO layer uses manual JDBC for full control and learning purposes.
- Swagger UI and Javadoc provide interactive and developer-facing documentation.
- JaCoCo report shows strong test coverage across all layers.
- A console-based Java frontend is available in the linked repo above.
- The app is modular and production-ready, with well-documented code and realistic seed data.

---

## 🙌 Thank You

Huge thanks to the Cognixia team for the incredible training and support. I'm proud of what I built here and excited to continue growing as a backend developer.

— Jacob Miller
