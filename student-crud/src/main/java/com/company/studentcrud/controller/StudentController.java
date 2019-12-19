package com.company.studentcrud.controller;

import com.company.studentcrud.dto.Student;
import com.company.studentcrud.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id).orElseThrow(() -> new IllegalArgumentException("Cannot find the student"));
    }

    @GetMapping("/student")
    public List<Student> getAllStudent() {
        return studentService.findAllStudents();
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("/student")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }
}
