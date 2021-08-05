package com.example.springboot.service.impl;

import com.example.springboot.exception.StudentNotFoundException;
import com.example.springboot.model.Student;
import com.example.springboot.repository.StudentRepository;
import com.example.springboot.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException());
    }

    @Override
    public void createStudent(Student student) {
        studentRepository.save(student);
    }
}
