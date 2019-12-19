package com.company.studentcrud.controller;

import com.company.studentcrud.dto.Course;
import com.company.studentcrud.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping("/course/student/{id}")
    public List<Course> getCourseByStudentId(@PathVariable int id) {
        return courseService.findByStudentId(id);
    }
}
