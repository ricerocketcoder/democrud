package com.gary.demo.crud.api.exception;

import com.gary.demo.crud.model.PersonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class DemoCrudExceptionHandler {

    @ExceptionHandler(value = { DataNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public PersonResponse dataNotFoundException(Exception ex, HttpServletRequest request) {
        return new PersonResponse(HttpStatus.NOT_FOUND, "Data Not Found");
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public PersonResponse applicationException(Exception ex, HttpServletRequest request) {
        return new PersonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Sever Error");
    }
}
