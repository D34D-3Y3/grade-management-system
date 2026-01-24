package com.company.grades.controller;

import com.company.grades.dto.EnrollmentDTO;
import com.company.grades.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentDTO> enroll(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        // Logic for linking Student and Course is handled in the ServiceImpl
        return new ResponseEntity<>(enrollmentService.enrollStudent(enrollmentDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }
}