package com.willioncommerce.wicommerce_backend.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String message;
    private String path;

    public ErrorResponse(HttpStatus status, String message, String path) {
        this.status = status.value();
        this.message = message;
        this.path = path;
    }
}