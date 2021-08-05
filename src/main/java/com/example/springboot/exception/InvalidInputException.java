package com.example.springboot.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(final String message){
        super(message);
    }
}
