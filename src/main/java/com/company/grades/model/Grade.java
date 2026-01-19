package com.company.grades.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Double midterm;
    private Double finalExam;
    private Double average;
    
    @Enumerated(EnumType.STRING)
    private GradeResult result;

    // Standard Getters and Setters
    public Double getMidterm() { return midterm; }
    public void setMidterm(Double midterm) { this.midterm = midterm; }
    public Double getFinalExam() { return finalExam; }
    public void setFinalExam(Double finalExam) { this.finalExam = finalExam; }
    public Double getAverage() { return average; }
    public void setAverage(Double average) { this.average = average; }
    public GradeResult getResult() { return result; }
    public void setResult(GradeResult result) { this.result = result; }
}