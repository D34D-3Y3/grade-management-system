package com.company.grades.mapper;

import com.company.grades.dto.CourseDTO;
import com.company.grades.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDto(Course course);
    Course toEntity(CourseDTO courseDTO);
}