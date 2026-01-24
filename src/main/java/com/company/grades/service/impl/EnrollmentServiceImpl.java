package com.company.grades.service.impl;

import com.company.grades.dto.EnrollmentDTO;
import com.company.grades.mapper.EnrollmentMapper;
import com.company.grades.model.Enrollment;
import com.company.grades.repository.CourseRepository;
import com.company.grades.repository.EnrollmentRepository;
import com.company.grades.repository.StudentRepository;
import com.company.grades.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentDTO enrollStudent(EnrollmentDTO dto) {
        // Find entities first to ensure they exist
        var student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        var course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        return enrollmentMapper.toDto(enrollmentRepository.save(enrollment));
    }

    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll().stream().map(enrollmentMapper::toDto).toList();
    }
}