package com.company.grades.controller;

import com.company.grades.model.Grade;
import com.company.grades.service.GradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // New Import for Spring Boot 3.4+
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean // Replaces @MockBean for Spring Boot 3.4+
    private GradeService gradeService;

    @Test
    public void testFullGradeEntryFlow() throws Exception {
        UUID enrollmentId = UUID.randomUUID();
        Grade gradeRequest = new Grade();
        gradeRequest.setMidterm(80.0);
        gradeRequest.setFinalExam(90.0);

        mockMvc.perform(post("/api/v1/enrollments/" + enrollmentId + "/grade")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gradeRequest)))
                .andExpect(status().isOk());
    }
}