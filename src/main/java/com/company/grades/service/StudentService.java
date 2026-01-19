package com.company.grades.service;

import com.company.grades.model.Student;
import com.company.grades.dto.StudentDTO;
import com.company.grades.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(StudentDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setStudentNo(dto.getStudentNo());
        return studentRepository.save(student);
    }

    public Student getById(UUID id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    public Object getTranscript(UUID id) {
        // Basic implementation for Section 6.3 requirement
        return studentRepository.findById(id).map(s -> "Transcript for " + s.getFirstName()).orElse("Not Found");
    }

    public Student updateStudent(UUID id, StudentDTO dto) {
        Student student = getById(id);
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        return studentRepository.save(student);
    }
}