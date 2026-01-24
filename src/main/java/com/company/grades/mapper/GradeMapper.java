package com.company.grades.mapper;

import com.company.grades.dto.GradeDTO;
import com.company.grades.model.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    // We map the enrollment inside the grade to a simple enrollmentId in the DTO
    // This prevents infinite loops (Grade -> Enrollment -> Grade -> ...)
    @Mapping(source = "enrollment.id", target = "enrollmentId")
    GradeDTO toDto(Grade grade);

    // When creating an entity from DTO, we might not have the full Enrollment object yet.
    // Usually, the Service layer handles fetching the Enrollment by ID and setting it manually.
    // So we ignore the enrollment field during simple mapping to avoid null pointer errors.
    @Mapping(target = "enrollment", ignore = true)
    Grade toEntity(GradeDTO gradeDTO);
}