package com.company.grades.service;

import com.company.grades.dto.EnrollmentDTO;
import java.util.List;

public interface EnrollmentService {
    EnrollmentDTO enrollStudent(EnrollmentDTO enrollmentDTO);
    List<EnrollmentDTO> getAllEnrollments();
}