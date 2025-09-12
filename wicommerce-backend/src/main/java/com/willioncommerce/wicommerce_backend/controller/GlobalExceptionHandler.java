package com.willioncommerce.wicommerce_backend.controller;

import com.willioncommerce.wicommerce_backend.exception.ErrorResponse;
import com.willioncommerce.wicommerce_backend.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        String path = (String) request.getAttribute("javax.servlet.forward.request_uri", WebRequest.SCOPE_REQUEST);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), path));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            errors.put(field, error.getDefaultMessage());
        });
        String path = (String) request.getAttribute("javax.servlet.forward.request_uri", WebRequest.SCOPE_REQUEST);
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, "Validation failed: " + errors, path);
        return ResponseEntity.badRequest().body(response);
    }

    // Fallback for unhandled RuntimeExceptions (e.g., DB errors)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex, WebRequest request) {
        String path = (String) request.getAttribute("javax.servlet.forward.request_uri", WebRequest.SCOPE_REQUEST);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), path));
    }
}