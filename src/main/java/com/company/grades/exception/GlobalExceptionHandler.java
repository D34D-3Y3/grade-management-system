package com.company.grades.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Centralized error management requirement
public class GlobalExceptionHandler {

    // Handles 404 - Not Found (e.g., Student not found)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntime(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

 // Handles 400 - Bad Request (e.g., Grade out of 0-100 range)
 // This satisfies the "400" requirement in your Checklist
 @ExceptionHandler(IllegalArgumentException.class)
 public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex) {
     Map<String, String> response = new HashMap<>();
     response.put("error", ex.getMessage());
     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Fixed line
 }

    // Handles 409 - Conflict (e.g., Duplicate enrollment)
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalState(IllegalStateException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}