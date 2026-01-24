package com.company.grades.service;

import com.company.grades.dto.CourseDTO;
import java.util.List;

public interface CourseService {
    CourseDTO createCourse(CourseDTO courseDTO);
    List<CourseDTO> getAllCourses();
}