package com.example.hw49.errors;

import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    private ErrorResponse noSuchElementHandler(NoSuchElementException exception) {
        log.error("Exception message: {}", exception.getMessage());
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage()).build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ErrorResponse resourceNotFoundException(ResourceNotFoundException exception) {
        log.error("Exception message: {}", exception.getMessage());
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage()).build();
    }

    @ExceptionHandler(InvalidDefinitionException.class)
    private ErrorResponse resourceNotFoundException(InvalidDefinitionException exception) {
        log.error("Exception message: {}", exception.getMessage());
        return ErrorResponse.builder(exception, HttpStatus.NOT_ACCEPTABLE, exception.getMessage()).build();
    }

    @ExceptionHandler(NullPointerException.class)
    private ErrorResponse noSuchElementHandler(NullPointerException exception) {
        log.error("Exception message: {}", exception.getMessage());
        return ErrorResponse.builder(exception, HttpStatus.NOT_FOUND, exception.getMessage()).build();
    }
}
