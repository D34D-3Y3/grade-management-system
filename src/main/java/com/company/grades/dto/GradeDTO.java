package com.company.grades.dto;

import com.company.grades.model.GradeResult;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeDTO {
    
    private String id;

    @NotNull(message = "Midterm grade is required")
    @Min(0) @Max(100)
    private Double midterm;

    @NotNull(message = "Final grade is required")
    @Min(0) @Max(100)
    private Double finalExam;

    // These are read-only for the client (calculated by Service)
    private Double average;
    private GradeResult result;

    @NotBlank(message = "Enrollment ID is required to link grades")
    private String enrollmentId;
}