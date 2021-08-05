package com.example.springboot.controller;

import com.example.springboot.exception.InvalidInputException;
import com.example.springboot.model.Student;
import com.example.springboot.service.impl.StudentServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/api")
@Log4j2
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;

    /**
     * Constructor dependency injection
     * https://springframework.guru/best-practices-for-dependency-injection-with-spring/
     *
     * @param studentServiceImpl
     */
    public StudentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    /**
     * Return the courses from a particular student
     *
     * @param studentId
     */
    @GetMapping("/students/{studentId}/courses")
    public void getCourses(@PathVariable String studentId) {
        log.info("Call method getCourses - studentId=" + studentId);
    }

    /**
     * Return all the students
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        log.info("Call method getStudents");
        return new ResponseEntity<>(this.studentServiceImpl.getStudents(), HttpStatus.OK);
    }

    /**
     * Returns a student by studentId
     *
     * @param studentId
     */
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable Long studentId) {
        log.info("Call method getStudent - studentId=" + studentId);

        if (!NumberUtils.isDigits(String.valueOf(studentId))){
            throw new InvalidInputException("StudentId  is not valid!");
        }
        return new ResponseEntity<>(studentServiceImpl.getStudent(studentId), HttpStatus.OK);
    }

    /**
     * Creates a student
     *
     * @param student
     */
    @PostMapping("/students")
    public void createStudent(@RequestBody Student student) {
        log.info("Call method createStudent");
        studentServiceImpl.createStudent(student);
    }

    /**
     * Deletes a student
     *
     * @param studentId
     */
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable String studentId) {
        log.info("Call method deleteStudent");
    }

    /**
     * Updates a student
     *
     * @param student
     */
    @PutMapping("/students/{studentId")
    public void updateStudent(@RequestBody Student student) {
        log.info("Call method updateStudent");
    }
}
