package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> noSuchElementException(EntityNotFoundException exception, WebRequest request) {
//        WeatherExceptionResponse details = new WeatherExceptionResponse(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
