package com.company.grades.repository;

import com.company.grades.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> { 
    // The 'String' here matches the @Id type in your Student entity
}