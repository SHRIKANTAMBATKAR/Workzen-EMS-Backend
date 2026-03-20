package com.workzen.ems.dto;

public class LoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
    private Boolean active;
    private String message;
    private String token;

    public LoginResponseDTO() {}

    public LoginResponseDTO(Long id, String name, String email, String role, Boolean active, String message, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = active;
        this.message = message;
        this.token = token;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    /*
     ## Key Accomplishments

     ### 1. Trainer Dashboard Reliability
     - **Auth Sync**: Corrected `localStorage` keys to use `currentUser`, enabling the dashboard to correctly identify the logged-in trainer.
     - **Data Mapping**: Aligned frontend fields with the Spring Boot API (`batchName` and `topicCovered`).
     - **Schedule Support**: Added missing `startTime` and `days` fields to the `Batch` entity and DTO.

     ### 2. Full File Upload System
     - **Backend Persistence**:
         - Enhanced the `BatchProgress` entity with `@Lob` binary storage for PDFs and other documents.
         - Added a `/api/sessionLogs/upload` endpoint supporting `multipart/form-data`.
     - **Frontend Integration**:
         - Updated `BatchProgress.jsx` to send files using `FormData`.
         - Implemented binary download logic in `ManageRecords.jsx` to retrieve and download PDFs from the database.

     ### 3. JWT Authentication Integration
     - **Secure Communication**:
         - Replaced simulated tokens with industry-standard JWT (JSON Web Token) authentication.
         - Protected all API endpoints (excluding login/auth) using Spring Security.
     - **Backend implementation**:
         - Configured a stateless `SecurityFilterChain` with a custom `JwtAuthFilter`.
         - Implemented a unified `UserDetailsService` that authenticates across all roles (Admin, Trainer, Analyst, Counselor).
     - **Frontend implementation**:
         - Added an Axios request interceptor in `api.js` to automatically attach the `Authorization: Bearer <token>` header to every request.
         - Updated `authService.js` to persist the real token from the backend.
    */

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}