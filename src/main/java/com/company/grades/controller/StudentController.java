package com.company.grades.controller;

import com.company.grades.dto.StudentDTO;
import com.company.grades.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.company.grades.service.StudentService;
import jakarta.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody @Valid StudentDTO studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAll(Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @GetMapping("/{id}/transcript")
    public ResponseEntity<Object> getTranscript(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getTranscript(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable UUID id, @RequestBody @Valid StudentDTO studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}