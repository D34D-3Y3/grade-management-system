package com.company.grades.dto;

import jakarta.validation.constraints.*;

public class StudentDTO {
    @NotBlank private String studentNo;
    @NotBlank private String firstName;
    @NotBlank private String lastName;
    @Email private String email;

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}