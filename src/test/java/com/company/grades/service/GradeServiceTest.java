package com.company.grades.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.company.grades.dto.GradeDTO;
import com.company.grades.mapper.GradeMapper;
import com.company.grades.model.Enrollment;
import com.company.grades.model.Grade;
import com.company.grades.model.GradeResult;
import com.company.grades.repository.EnrollmentRepository;
import com.company.grades.repository.GradeRepository;
import com.company.grades.service.impl.GradeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private GradeMapper gradeMapper;

    @InjectMocks
    private GradeServiceImpl gradeService; // Testing the implementation

    private GradeDTO inputDto;
    private Enrollment mockEnrollment;
    private Grade mockGrade;

    @BeforeEach
    void setUp() {
        // Prepare a sample input DTO
        inputDto = GradeDTO.builder()
                .enrollmentId("env-123")
                .midterm(50.0)
                .finalExam(70.0)
                .build();

        mockEnrollment = new Enrollment();
        mockEnrollment.setId("env-123");

        mockGrade = new Grade();
    }

    @Test
    @DisplayName("Should calculate average as 62.0 and result as GECTI (40/60 weight)")
    void shouldCalculatePassingGrade() {
        // Arrange: Define behavior for mocks
        when(enrollmentRepository.findById("env-123")).thenReturn(Optional.of(mockEnrollment));
        when(gradeMapper.toEntity(any(GradeDTO.class))).thenReturn(mockGrade);
        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(gradeMapper.toDto(any(Grade.class))).thenAnswer(invocation -> {
            Grade g = invocation.getArgument(0);
            return GradeDTO.builder()
                    .average(g.getAverage())
                    .result(g.getResult())
                    .build();
        });

        // Act: Execute the service method
        GradeDTO resultDto = gradeService.submitGrade(inputDto);

        // Assert: Verify the 40/60 logic
        // (50 * 0.4) + (70 * 0.6) = 20 + 42 = 62
        assertEquals(62.0, resultDto.getAverage(), "Average should be (50*0.4) + (70*0.6) = 62.0");
        assertEquals(GradeResult.GECTI, resultDto.getResult(), "Result should be GECTI for average >= 60");
        
        verify(gradeRepository, times(1)).save(any(Grade.class));
    }

    @Test
    @DisplayName("Should calculate average as 54.0 and result as KALDI")
    void shouldCalculateFailingGrade() {
        // Arrange: Set grades that will fail (30 mid, 70 final)
        inputDto.setMidterm(30.0);
        inputDto.setFinalExam(70.0);
        // (30 * 0.4) + (70 * 0.6) = 12 + 42 = 54

        when(enrollmentRepository.findById("env-123")).thenReturn(Optional.of(mockEnrollment));
        when(gradeMapper.toEntity(any(GradeDTO.class))).thenReturn(mockGrade);
        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(gradeMapper.toDto(any(Grade.class))).thenAnswer(invocation -> {
            Grade g = invocation.getArgument(0);
            return GradeDTO.builder().average(g.getAverage()).result(g.getResult()).build();
        });

        // Act
        GradeDTO resultDto = gradeService.submitGrade(inputDto);

        // Assert
        assertEquals(54.0, resultDto.getAverage());
        assertEquals(GradeResult.KALDI, resultDto.getResult());
    }
}