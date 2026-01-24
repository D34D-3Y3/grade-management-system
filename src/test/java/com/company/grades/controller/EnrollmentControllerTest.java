package com.company.grades.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.company.grades.dto.EnrollmentDTO;
import com.company.grades.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentService enrollmentService; // Mocking the interface

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/v1/enrollments - Should return 201 Created")
    void shouldEnrollStudent() throws Exception {
        // 1. Prepare DTO
        EnrollmentDTO requestDto = EnrollmentDTO.builder()
                .studentId("std-1")
                .courseId("crs-1")
                .build();

        EnrollmentDTO responseDto = EnrollmentDTO.builder()
                .id("enr-100")
                .studentId("std-1")
                .courseId("crs-1")
                .build();

        // 2. Mock Service behavior
        when(enrollmentService.enrollStudent(any(EnrollmentDTO.class))).thenReturn(responseDto);

        // 3. Perform Request and Assert
        mockMvc.perform(post("/api/v1/enrollments")
                .contentType(MediaType.APPLICATION_JSON) // FIX IS HERE
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("enr-100"))
                .andExpect(jsonPath("$.studentId").value("std-1"));
    }
}