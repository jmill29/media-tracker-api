# TV Tracker API – Cognixia Capstone Project

This is the backend API for my Cognixia Future Horizons Capstone Project. It’s a Java-based Spring Boot REST API that allows users to track their TV show watch history. This project demonstrates secure user authentication, layered architecture, and robust exception handling.

## 🎯 Project Objective

To design and build a secure, maintainable, and fully functional backend application for managing a user's personalized TV watch history using Spring Boot and MySQL. This project is intended to showcase my backend development skills, including authentication, data persistence, and RESTful design.

## ✅ Key Features

- **User Authentication** via Spring Security (`JdbcUserDetailsManager`)
- **BCrypt-encrypted passwords** for secure login
- **Track watch status** (e.g., Watching, Completed, Plan to Watch) per show
- **REST API design** using Controller-Service-DAO architecture
- **Global Exception Handling** using `@ControllerAdvice`
- **Modular and Testable Codebase**
- **SQL seed script** for fast local setup

## 📌 Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET`  | `/api/shows` | Fetch all TV shows |
| `GET`  | `/api/watch-history` | Fetch authenticated user's tracked shows |
| `POST` | `/api/watch-history` | Add a new tracked show with watch status |
| `PUT`  | `/api/watch-history` | Update watch status of a tracked show |
| `DELETE` | `/api/watch-history/{showId}` | Remove a show from watch history |

> 🔐 All `watch-history` endpoints require Basic Auth using valid user credentials

## 🛠 Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JDBC (no Hibernate)
- MySQL (local instance)
- Maven

## 🚀 How to Run Locally

1. Clone the project:
   ```bash
   git clone https://github.com/jmill29/tv-show-tracker-api.git
   cd tv-show-tracker-api
   ```

2. Set up the MySQL database:
   - Open MySQL Workbench (or other client)
   - Run `src/main/resources/capstone_schema_seed.sql`

3. Configure `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tv_show_tracker
   spring.datasource.username=your_mysql_user
   spring.datasource.password=your_mysql_password
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## 🧪 Example Usage

**POST /api/watch-history**

```json
{
  "show_id": 3,
  "status": "Watching"
}
```

With header:
```
Authorization: Basic base64encoded(username:password)
```

## 💡 Notes for Evaluators

- I used a **layered architecture** with clear separation of concerns
- Passwords are **securely hashed** using BCrypt
- DAO layer uses **manual JDBC** (no Spring Data JPA)
- The app is **production-ready** and modular enough to support future enhancements
- I plan to build a **console-based frontend** as the next phase
- The code is **well-documented**, and the SQL script provides test users and data

## 🙌 Thank You

Thank you to the Cognixia team for the excellent training and support. I welcome any feedback to improve this project and grow as a backend developer.

— Jacob Miller
