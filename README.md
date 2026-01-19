# Student Grade Management System

A Spring Boot-based RESTful API designed to manage students, courses, and grade calculations using a layered architecture.

## 🚀 Getting Started

### Prerequisites
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* [Junit 5](https://junit.org/) (for testing API endpoints)

### Running the Project
The entire system (Application + PostgreSQL Database) is containerized. To start the project, run the following command in the root directory:

```bash
docker-compose up --build🎓 Student Grade Management System

A robust Spring Boot REST API for managing students, courses, enrollments, and grade calculations based on a weighted average of 40% midterm and 60% final exam scores.
🏗 Architectural Compliance

This project strictly follows the dictated Mimari Kurallar:

    Layered Separation: The Controller remains a thin HTTP layer, while all complex business logic (grade calculation) is centralized in the Service layer.

    Repository Isolation: Repositories are used exclusively for DB access via PostgreSQL.

    DTO Pattern: Database Entities are never exposed to the outside world; DTOs are used for all API responses.

    Centralized Error Handling: Exception management is handled at a single point using @RestControllerAdvice, returning meaningful HTTP codes (400,404,409).

    Bean Validation: Data integrity is ensured using @Valid along with @NotNull, @Min, and @Max annotations.

    Clean Code: Lombok is avoided to ensure manual control over POJOs as requested.

🚀 Getting Started (Docker)

docker-compose up --build

The application will be accessible at http://localhost:8080.
🧪 Testing and Proof of Logic

This project includes a comprehensive test suite to satisfy the Minimum Test Beklentisi:

    GradeServiceTest.java: A Unit Test proving the 40/60 weighted average calculation and the resulting GECTI or KALDI status.

    EnrollmentControllerTest.java: An Integration Test using MockMvc to verify the full API flow for grade entry.

To run tests manually:
Bash

mvn clean test

🛠 API Usage & Evaluation Guide

To verify the system logic, follow this sequence:
1. Create a Student

POST /api/v1/students
JSON

{
  "studentNo": "S101",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}

2. Enter Grade & Calculate Result

POST /api/v1/enrollments/{id}/grade
JSON

{
  "midterm": 70,
  "finalExam": 80
}

Expected Outcome:

    Average: 76.0 (70×0.4+80×0.6).

    Result: GECTI (Threshold is 60.0).

📦 Project Structure
Plaintext

src/main/java/com/company/grades/
├── controller/  # HTTP Endpoints
├── service/     # Business Logic
├── repository/  # Data Access
├── model/       # Entities & Enums
├── dto/         # Data Transfer Objects
└── exception/   # Global Error Handlergrades-management-system/
├── src/
│   ├── main/
│   │   ├── java/com/company/grades/
│   │   │   ├── controller/          <-- HTTP Layer (Endpoint'ler çalışıyor)
│   │   │   │   ├── CourseController.java
│   │   │   │   ├── EnrollmentController.java
│   │   │   │   └── StudentController.java
│   │   │   ├── service/             <-- Business Logic (Grade hesaplama burada)
│   │   │   │   ├── CourseService.java
│   │   │   │   ├── EnrollmentService.java
│   │   │   │   ├── GradeService.java
│   │   │   │   └── StudentService.java
│   │   │   ├── repository/          <-- DB Access (PostgreSQL integration)
│   │   │   │   ├── CourseRepository.java
│   │   │   │   ├── EnrollmentRepository.java
│   │   │   │   ├── GradeRepository.java
│   │   │   │   └── StudentRepository.java
│   │   │   ├── model/               <-- Entities & Enums
│   │   │   │   ├── Course.java
│   │   │   │   ├── Enrollment.java
│   │   │   │   ├── Grade.java
│   │   │   │   ├── GradeResult.java
│   │   │   │   └── Student.java
│   │   │   ├── dto/                 <-- Data Transfer Objects (Entity sızmıyor)
│   │   │   │   └── StudentDTO.java
│   │   │   ├── exception/           <-- @RestControllerAdvice (Hata yönetimi)
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── GradesApplication.java
│   │   └── resources/
│   │       └── application.yml      <-- Configuration
│   └── test/java/com/company/grades/
│       ├── controller/              <-- Integration Tests (MockMvc)
│       │   └── EnrollmentControllerTest.java
│       └── service/                 <-- Unit Tests (Grade hesaplama)
│           └── GradeServiceTest.java
├── Dockerfile                       <-- App build
