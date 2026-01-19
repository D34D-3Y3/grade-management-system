package com.company.grades.repository;

import com.company.grades.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// JpaRepository<EntityName, PrimaryKeyType>
public interface StudentRepository extends JpaRepository<Student, UUID> {
}