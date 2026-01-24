package com.company.grades.mapper;

import com.company.grades.dto.StudentDTO;
import com.company.grades.model.Student;
import org.mapstruct.Mapper;

// componentModel="spring" tells MapStruct to create a Spring Bean
// so you can use @Autowired in your ServiceImpl.
@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);
}