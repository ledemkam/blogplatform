package com.kte.blog.controllers;

import com.kte.blog.domain.response.AuthResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestController
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AuthResponse.ApiErrorResponse> handleException(Exception ex) {
        log.error("Caught exception", ex);
        AuthResponse.ApiErrorResponse error = AuthResponse.ApiErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("An unexpected error occurred")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AuthResponse.ApiErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        AuthResponse.ApiErrorResponse error = AuthResponse.ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<AuthResponse.ApiErrorResponse> handleIllegalStateException(
            IllegalStateException ex) {
        AuthResponse.ApiErrorResponse error = AuthResponse.ApiErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AuthResponse.ApiErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        AuthResponse.ApiErrorResponse error = AuthResponse.ApiErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message("Incorrect username or password")
                .build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AuthResponse.ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        AuthResponse.ApiErrorResponse error = AuthResponse.ApiErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<AuthResponse.ApiErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        AuthResponse.ApiErrorResponse error = AuthResponse.ApiErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Resource not found")
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
