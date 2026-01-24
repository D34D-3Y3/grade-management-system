package com.company.grades.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Min(value = 0, message = "Midterm cannot be less than 0")
    @Max(value = 100, message = "Midterm cannot be more than 100")
    @Column(nullable = false)
    // Midterm exam score (Weights 40% in calculation)
    private Double midterm;

    @Min(value = 0, message = "Final cannot be less than 0")
    @Max(value = 100, message = "Final cannot be more than 100")
    @Column(nullable = false)
    // Final exam score (Weights 60% in calculation)
    private Double finalExam;

    @Column(nullable = true)
    // Calculated field: (midterm * 0.4) + (final * 0.6). Can be null before calculation.
    private Double average;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    // Derived status: GECTI (Pass) or KALDI (Fail) based on average >= 60
    private GradeResult result;

    @OneToOne(optional = false)
    @JoinColumn(name = "enrollment_id", unique = true, nullable = false)
    // Links these grades to a specific enrollment record
    private Enrollment enrollment;
}