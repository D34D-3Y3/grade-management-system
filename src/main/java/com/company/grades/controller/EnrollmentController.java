package com.company.grades.controller;

import com.company.grades.model.Enrollment;
import com.company.grades.model.Grade;
import com.company.grades.service.EnrollmentService;
import com.company.grades.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final GradeService gradeService;

    public EnrollmentController(EnrollmentService enrollmentService, GradeService gradeService) {
        this.enrollmentService = enrollmentService;
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@RequestBody @Valid Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(enrollment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    /**
     * Endpoint for Section 6.3: Entering grades.
     * Triggers calculation of average (40/60) and result (GECTI/KALDI).
     */
    @PostMapping("/{id}/grade")
    public ResponseEntity<Grade> addGrade(@PathVariable UUID id, @RequestBody Grade gradeRequest) {
        // Business logic for average and result calculation is kept in GradeService
        return ResponseEntity.ok(gradeService.processGradeEntry(id, gradeRequest));
    }
}