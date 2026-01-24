package com.company.grades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "students")
@Data                 // Generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor    // Required by JPA specification
@AllArgsConstructor   // Useful for testing and builder pattern
@Builder              // Enables cleaner object creation: Student.builder().name("John").build()
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    // Primary key: UUID is safer than Integer for distributed systems
    private String id;

    @NotBlank(message = "Student number cannot be empty")
    @Column(nullable = false, unique = true)
    // Unique identifier assigned by the institution (e.g., "2023001")
    private String studentNo;

    @NotBlank(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    // Communication channel; must be unique in the system
    private String email;
}