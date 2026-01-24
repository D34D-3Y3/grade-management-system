package com.company.grades.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Course code is required")
    @Column(nullable = false, unique = true)
    // e.g., "CS101", "MAT202". Must be unique to identify the subject.
    private String code;

    @NotBlank(message = "Course name is required")
    @Column(nullable = false)
    // Descriptive name, e.g., "Introduction to Computer Science"
    private String name;
}