package com.example.springboot.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(){
        super("Student was not found");
    }
}
