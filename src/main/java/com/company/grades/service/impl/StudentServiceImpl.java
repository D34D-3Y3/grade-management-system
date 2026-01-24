package com.company.grades.service.impl;

import com.company.grades.dto.StudentDTO;
import com.company.grades.mapper.StudentMapper;
import com.company.grades.model.Student;
import com.company.grades.repository.StudentRepository;
import com.company.grades.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Injects Repository and Mapper via constructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        // Business Rule: Map DTO to Entity for DB storage
        Student student = studentMapper.toEntity(studentDTO);
        return studentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Override
    public StudentDTO getStudentById(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDto(student);
    }
}