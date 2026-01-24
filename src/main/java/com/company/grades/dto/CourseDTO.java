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
public class CourseDTO {
    
    private String id;

    @NotBlank(message = "Course code is mandatory (e.g., CS101)")
    private String code;

    @NotBlank(message = "Course name is mandatory")
    private String name;
}