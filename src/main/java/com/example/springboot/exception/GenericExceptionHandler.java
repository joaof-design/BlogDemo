package com.example.springboot.exception;

import com.example.springboot.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = InvalidInputException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidInputException(InvalidInputException ex) {
        return ErrorMessage.builder().message(ex.getMessage()).timestamp(new Date()).code("0").build();
    }

    @ExceptionHandler(value = StudentNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleStudentNotFoundException(StudentNotFoundException ex) {
        return ErrorMessage.builder().message(ex.getMessage()).timestamp(new Date()).code("0").build();
    }

    @ExceptionHandler(value = Exception.class)
    public ErrorMessage handleAnyException(Exception ex){
        return ErrorMessage.builder().message(ex.getMessage()).timestamp(new Date()).code("0").build();
    }
}
