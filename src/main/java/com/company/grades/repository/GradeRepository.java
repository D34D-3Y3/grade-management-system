package com.company.grades.repository;

import com.company.grades.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface GradeRepository extends JpaRepository<Grade, UUID> {
    // This connects to the Enrollment 1-1 Grade relationship [cite: 44]
}