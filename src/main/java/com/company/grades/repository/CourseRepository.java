package com.company.grades.repository;

import com.company.grades.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    // Spring will automatically handle 'code unique' constraint from the entity 
}