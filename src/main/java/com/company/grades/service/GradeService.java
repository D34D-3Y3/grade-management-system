package com.company.grades.service;

import com.company.grades.model.Grade;
import com.company.grades.model.GradeResult;
import com.company.grades.repository.GradeRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GradeService {
    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade processGradeEntry(UUID enrollmentId, Grade gradeRequest) {
        // Business Rule validation [cite: 35]
        if (gradeRequest.getMidterm() < 0 || gradeRequest.getMidterm() > 100) {
            throw new IllegalArgumentException("Vize 0-100 aralığında olmalı.");
        }

        // Calculation [cite: 36]
        double avg = (gradeRequest.getMidterm() * 0.4) + (gradeRequest.getFinalExam() * 0.6);
        gradeRequest.setAverage(avg);

        // Success result [cite: 37]
        gradeRequest.setResult(avg >= 60 ? GradeResult.GECTI : GradeResult.KALDI);

        return gradeRepository.save(gradeRequest);
    }
}