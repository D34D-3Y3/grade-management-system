package com.company.grades.service.impl;

import com.company.grades.dto.GradeDTO;
import com.company.grades.mapper.GradeMapper;
import com.company.grades.model.Grade;
import com.company.grades.model.GradeResult;
import com.company.grades.repository.EnrollmentRepository;
import com.company.grades.repository.GradeRepository;
import com.company.grades.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final GradeMapper gradeMapper;

    @Override
    public GradeDTO submitGrade(GradeDTO dto) {
        var enrollment = enrollmentRepository.findById(dto.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment record not found"));

        // Business Logic: 40% Midterm + 60% Final
        double average = (dto.getMidterm() * 0.4) + (dto.getFinalExam() * 0.6);
        GradeResult result = (average >= 60.0) ? GradeResult.GECTI : GradeResult.KALDI;

        Grade grade = gradeMapper.toEntity(dto);
        grade.setEnrollment(enrollment);
        grade.setAverage(average);
        grade.setResult(result);

        return gradeMapper.toDto(gradeRepository.save(grade));
    }
}