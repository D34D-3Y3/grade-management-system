package com.company.grades.service;

import com.company.grades.model.Grade;
import com.company.grades.model.GradeResult;
import com.company.grades.repository.GradeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Test
    @DisplayName("Should calculate 40/60 weighted average and return GECTI when >= 60")
    void testProcessGradeEntry_Success() {
        // Arrange
        UUID enrollmentId = UUID.randomUUID();
        Grade gradeRequest = new Grade();
        gradeRequest.setMidterm(70.0);    // 70 * 0.4 = 28
        gradeRequest.setFinalExam(80.0);  // 80 * 0.6 = 48 -> Total: 76

        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Grade result = gradeService.processGradeEntry(enrollmentId, gradeRequest);

        // Assert
        assertAll(
            () -> assertEquals(76.0, result.getAverage(), "Average calculation should be (70*0.4)+(80*0.6)"),
            () -> assertEquals(GradeResult.GECTI, result.getResult(), "Result should be GECTI for average 76")
        );
    }

    @Test
    @DisplayName("Should return KALDI when weighted average is below 60")
    void testProcessGradeEntry_Failure() {
        // Arrange
        Grade gradeRequest = new Grade();
        gradeRequest.setMidterm(40.0);    // 16
        gradeRequest.setFinalExam(50.0);  // 30 -> Total: 46

        when(gradeRepository.save(any(Grade.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Grade result = gradeService.processGradeEntry(UUID.randomUUID(), gradeRequest);

        // Assert
        assertEquals(46.0, result.getAverage());
        assertEquals(GradeResult.KALDI, result.getResult());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when grades are out of 0-100 range")
    void testProcessGradeEntry_InvalidGrades() {
        // Arrange
        Grade gradeRequest = new Grade();
        gradeRequest.setMidterm(110.0); // Invalid
        gradeRequest.setFinalExam(50.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            gradeService.processGradeEntry(UUID.randomUUID(), gradeRequest);
        }, "Should throw exception for grades > 100");
    }
}