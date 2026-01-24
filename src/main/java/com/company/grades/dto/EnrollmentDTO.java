package com.company.grades.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDTO {
    
    private String id;

    @NotBlank(message = "Student ID is required for enrollment")
    private String studentId;

    @NotBlank(message = "Course ID is required for enrollment")
    private String courseId;
    
    // Optional fields to show name in response, but not required for request
    private String studentName; 
    private String courseName;
}