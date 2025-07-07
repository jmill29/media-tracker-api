# Media Tracker API – In Progress 🚧 [![Coverage](https://img.shields.io/badge/Coverage-80%25-brightgreen)](https://github.com/jmill29/media-tracker-api) [![WIP](https://img.shields.io/badge/status-in_progress-yellow.svg)](https://github.com/jmill29/media-tracker-frontend)

This is the backend REST API for **Media Tracker**, a full-stack project that helps users track their media consumption across **TV shows**, **books**, and **music**. It’s built with Java and Spring Boot using JDBC for database access.

> ⚠️ **This project is actively being developed.** What started as a TV-only tracker for my Cognixia Capstone is now evolving into a complete multi-media tracking system.

---

## 🎯 Project Objective

To create a secure, scalable backend that enables users to track and manage their watch, read, and listen histories across various media formats — while maintaining a clean, modular architecture and high test coverage.

---

## ✅ Current Features (TV Module)

- 🔐 **Basic Auth Authentication** using Spring Security and `JdbcUserDetailsManager`
- 🔒 **BCrypt Password Hashing**
- 📺 **TV Show Watch History Tracking** (Watch, Complete, Plan to Watch)
- 📂 **Clean Architecture**: Controller → Service → DAO
- ❌ **Custom Exception Handling** with `@ControllerAdvice`
- 🧪 **Thorough Testing** with 80%+ coverage via JaCoCo
- 🔧 **SQL Seed Script** for easy local setup
- 📖 **Interactive API Docs** via Swagger UI
- 📝 **Javadoc HTML Output**

---

## 🗂️ Entity Relationship Diagram (ERD)

![ER Diagram](./ERDiagram.png)

---

## 📖 API Documentation

- 🔍 Swagger UI:  
  [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

- 📚 Javadoc HTML Docs:  
  [./docs/index.html](./docs/index.html)

---

## 📈 Test Coverage Highlights

- ✅ 170+ unit/integration tests
- 🧪 Tested layers: DAO, Service, Controller, and Exception handling
- 📊 80%+ line coverage with JaCoCo

---

## 📌 Core Endpoints (Current)

| Method | Endpoint                        | Description                       |
|--------|----------------------------------|-----------------------------------|
| GET    | `/api/shows`                    | Get all TV shows                  |
| GET    | `/api/watch-history`           | View user's watch history         |
| POST   | `/api/watch-history`           | Add a show to watch history       |
| PUT    | `/api/watch-history`           | Update status of tracked show     |
| DELETE | `/api/watch-history/{showId}`  | Remove show from watch history    |

> 🔐 All watch-history routes require valid Basic Auth credentials.

---

## 🛠 Tech Stack

- Java 17  
- Spring Boot  
- Spring Security  
- JDBC (manual)  
- MySQL  
- Swagger / Springdoc OpenAPI  
- Javadoc  
- JaCoCo  
- Maven

---

## 🚀 Local Setup Instructions

1. Clone the repo  
   `git clone https://github.com/jmill29/media-tracker-api.git`

2. Set up the MySQL database  
   Run the SQL file at `src/main/resources/media_tracker_schema_seed.sql`

3. Configure your `application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/media_tracker
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Start the app  
   `mvn spring-boot:run`

---

## 🧪 Example Request

**POST /api/watch-history**

```json
{
  "show_id": 3,
  "status": "Watching"
}
```

**Header**:  
`Authorization: Basic base64(username:password)`

---

## 🔮 Roadmap – What’s Coming

- ✅ Build out TV show tracking module *(complete)*
- 🛠️ Replace Basic Auth with JWT (Access + Refresh tokens)
- 🌐 Create a **React-based frontend** and deploy to S3 + CloudFront
- ☁️ Deploy backend to AWS (ECS or Fargate)
- 📚 Add support for **Books**
- 🎵 Add support for **Music**
- 🕓 Track full **media activity history** (status updates over time)
- 📊 Add analytics + personalized summaries

---

## 📂 Related Links

- 🖥️ [Frontend Repo (Media Tracker UI)](https://github.com/jmill29/media-tracker-frontend)
- 📋 [Media Tracker Kanban Board](https://github.com/users/jmill29/projects/1)
- 🧱 [Original Capstone API Repo](https://github.com/jmill29/tv-show-tracker-api)

---

## 🙌 Shoutout

Special thanks to the Cognixia team for kickstarting this journey. What began as a simple backend app is now growing into a full-stack product that I’m excited to build, refine, and eventually deploy to production.

— Jacob Miller
