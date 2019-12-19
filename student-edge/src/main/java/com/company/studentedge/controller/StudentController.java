package com.company.studentedge.controller;

import com.company.studentedge.model.StudentViewModel;
import com.company.studentedge.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentViewModel addStudent(@RequestBody StudentViewModel studentViewModel) {
        return studentService.addStudent(studentViewModel);
    }

    @GetMapping("/student/{id}")
    public StudentViewModel getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
}
