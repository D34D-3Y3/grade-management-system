package com.company.grades.service;

import com.company.grades.model.Enrollment;
import com.company.grades.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment getById(UUID id) {
        return enrollmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Enrollment not found with id: " + id));
    }

    public Enrollment enrollStudent(Enrollment enrollment) {
        // Business Rule: Check for duplicate enrollment
        boolean alreadyEnrolled = enrollmentRepository.existsByStudentIdAndCourseId(
            enrollment.getStudent().getId(), 
            enrollment.getCourse().getId()
        );

        if (alreadyEnrolled) {
            throw new IllegalStateException("Student is already enrolled in this course.");
        }

        return enrollmentRepository.save(enrollment);
    }
}