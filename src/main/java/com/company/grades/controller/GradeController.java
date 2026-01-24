package com.company.grades.controller;

import com.company.grades.dto.GradeDTO;
import com.company.grades.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<GradeDTO> submitGrade(@Valid @RequestBody GradeDTO gradeDTO) {
        // Calculation of Average and Pass/Fail result happens inside GradeServiceImpl
        return new ResponseEntity<>(gradeService.submitGrade(gradeDTO), HttpStatus.CREATED);
    }
}