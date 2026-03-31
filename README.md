# WorkZen EMS - Backend

WorkZen EMS Backend is a robust REST API built with Spring Boot to manage the IT Training Management System. It handles authentication, user management, student registration, batch scheduling, and progress tracking.

## 🛠️ Tech Stack

- **Framework**: [Spring Boot 3.5.11](https://spring.io/projects/spring-boot)
- **Language**: Java 17
- **Security**: Spring Security + JWT (JSON Web Token)
- **Database**: MySQL / PostgreSQL (configured via JPA)
- **ORM**: Spring Data JPA / Hibernate
- **Build Tool**: Maven
- **Utilities**: Lombok

## 🚀 Key Features

- **JWT Authentication**: Secure login and role-based access control.
- **User Management**: Comprehensive CRUD for Admins, Trainers, Analysts, and Counselors.
- **Batch Management**: Tools for Analysts to create and assign batches.
- **Progress Tracking**: APIs for Trainers to log session progress and attendance.
- **Student Management**: Endpoints for Counselor and Analyst workflows.

## ⚙️ Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL or PostgreSQL database

### Configuration

Update the database credentials in `src/main/resources/application.properties` or set the following environment variables:

- `SPRING_DATASOURCE_URL`: JDBC URL (default: `jdbc:mysql://localhost:3306/workzen_ems`)
- `SPRING_DATASOURCE_USERNAME`: Database username (default: `root`)
- `SPRING_DATASOURCE_PASSWORD`: Database password

### Installation & Running

1. Clone the repository and navigate to the backend folder.
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
The API will be available at `http://localhost:8080`.

## 📂 API Overview

- `POST /api/auth/login`: Authenticate and receive a JWT token.
- `GET /api/batches`: Retrieve all batches.
- `POST /api/users`: Create new system users (Admin only).
- `GET /api/students`: Manage student records.
