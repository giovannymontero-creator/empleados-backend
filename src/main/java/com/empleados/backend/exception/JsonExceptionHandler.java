package com.empleados.backend.exception;

import java.util.Arrays;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "{com.empleados.backend.controllers}")
public class JsonExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleAllOtherErrors(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(exception.getMessage()));
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public final String handleConstraintException(ConstraintViolationException ex, WebRequest request) {
        //Do and return whatever you want
        return request+" - "+Arrays.toString(ex.getStackTrace());
    }

}
