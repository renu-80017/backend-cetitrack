# CertiTrack Backend (Spring Boot)

Simple backend for React Login/Signup + Google login flow.

## Tech Stack
- Spring Boot 3.1.12
- Java 17
- Maven
- MySQL

## Package
`com.certitrack.backend`

## Project Structure
```
com.certitrack.backend
│
├── controller
│   └── AuthController.java
├── service
│   └── UserService.java
├── repository
│   └── UserRepository.java
├── entity
│   └── User.java
├── dto
│   ├── RegisterRequest.java
│   ├── LoginRequest.java
│   ├── GoogleLoginRequest.java
│   └── AuthResponse.java
└── CertitrackBackendApplication.java
```

## MySQL Setup

### 1) Create database
```sql
CREATE DATABASE aidss2;
```

### 2) Use database
```sql
USE aidss2;
```

### 3) Create users table
```sql
CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL DEFAULT 'user',
  provider VARCHAR(50) NOT NULL DEFAULT 'manual',
  PRIMARY KEY (id)
);
```

### 4) Sample insert
```sql
INSERT INTO users (name, email, password, role, provider)
VALUES ('Renu', 'renu@gmail.com', '1234', 'user', 'manual');
```

## Configure database connection
Update `src/main/resources/application.properties` if your username/password is different:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/aidss2
spring.datasource.username=root
spring.datasource.password=root
```

## Run in Spring Tool Suite (STS) - Step by Step
1. Open STS.
2. Click **File > Import > Existing Maven Projects**.
3. Select this project folder (`backend-cetitrack-s2`).
4. Wait for Maven dependencies to download.
5. Ensure MySQL server is running.
6. Open `application.properties` and confirm DB credentials.
7. Run main class: `CertitrackBackendApplication`.
8. Verify app starts on `http://localhost:8080`.

## API Endpoints
Base URL: `http://localhost:8080/api/auth`

### 1) Register
**POST** `/register`

Request body:
```json
{
  "name": "Renu",
  "email": "renu@gmail.com",
  "password": "1234"
}
```

Success response:
```json
{
  "message": "User registered successfully",
  "status": true
}
```

If email exists:
```json
{
  "message": "Email already registered",
  "status": false
}
```

### 2) Login
**POST** `/login`

Request body:
```json
{
  "email": "renu@gmail.com",
  "password": "1234"
}
```

Success response:
```json
{
  "message": "Login successful",
  "status": true,
  "name": "Renu",
  "role": "user"
}
```

Invalid response:
```json
{
  "message": "Invalid email or password",
  "status": false
}
```

### 3) Google Login
**POST** `/google-login`

Request body:
```json
{
  "name": "Renu",
  "email": "renu@gmail.com"
}
```

Response:
```json
{
  "message": "Google login successful",
  "status": true,
  "name": "Renu",
  "role": "user"
}
```

## Thunder Client Testing in VS Code
1. Start Spring Boot app.
2. Open VS Code > Thunder Client extension.
3. Create new request.

### Register test
- Method: `POST`
- URL: `http://localhost:8080/api/auth/register`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "name": "Renu",
  "email": "renu@gmail.com",
  "password": "1234"
}
```
- Click **Send**.

### Login test
- Method: `POST`
- URL: `http://localhost:8080/api/auth/login`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "email": "renu@gmail.com",
  "password": "1234"
}
```
- Click **Send**.

### Google login test
- Method: `POST`
- URL: `http://localhost:8080/api/auth/google-login`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "name": "Renu",
  "email": "renu@gmail.com"
}
```
- Click **Send**.

## React Axios Example
```javascript
import axios from "axios";

const BASE_URL = "http://localhost:8080/api/auth";

export const registerUser = (data) => axios.post(`${BASE_URL}/register`, data);
export const loginUser = (data) => axios.post(`${BASE_URL}/login`, data);
export const googleLoginUser = (data) => axios.post(`${BASE_URL}/google-login`, data);
```

> No JWT/Spring Security is used in this version. Passwords are matched as plain text (for learning/demo).
