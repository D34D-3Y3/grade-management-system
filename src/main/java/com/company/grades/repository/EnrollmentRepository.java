package com.company.grades.repository;

import com.company.grades.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    // Required for the business rule: a student cannot enroll in the same course twice
    boolean existsByStudentIdAndCourseId(UUID studentId, UUID courseId);
}