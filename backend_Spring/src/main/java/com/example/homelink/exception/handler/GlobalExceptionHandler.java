package com.example.homelink.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.homelink.exception.shelter.BadRequestException;
import com.example.homelink.exception.shelter.ShelterNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    //404 NOT FOUND
    @ExceptionHandler(ShelterNotFoundException.class)
    public ResponseEntity<?> shelterNotFoundException(ShelterNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails("No Shelter(s)", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    //400 BAD REQUEST
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        ErrorDetails errorDetails = new ErrorDetails("Bad Request", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    //500 INTERNAL ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails("Error Occurred", ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
