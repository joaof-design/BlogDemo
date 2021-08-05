package com.example.springboot.service;

import com.example.springboot.model.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudents();

    public Student getStudent(final Long studentId);

    public void createStudent(final Student student);

}
