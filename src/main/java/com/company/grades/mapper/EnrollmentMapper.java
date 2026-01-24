package com.company.grades.mapper;

import com.company.grades.dto.EnrollmentDTO;
import com.company.grades.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "student.firstName", target = "studentName")
    @Mapping(source = "course.name", target = "courseName")
    EnrollmentDTO toDto(Enrollment enrollment);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    Enrollment toEntity(EnrollmentDTO enrollmentDTO);
}