package com.company.studentedge.feign;

import com.company.studentedge.model.Course;
import com.company.studentedge.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("student-crud")
public interface StudentClient {
    @GetMapping("/student/{id}")
    Student getStudentById(@PathVariable int id);

    @GetMapping("/course/student/{id}")
    List<Course> getCoursesByStudentId(@PathVariable int id);

    @PostMapping("/student")
    Student addStudent(@RequestBody Student student);

    @PostMapping("/course")
    Course addCourse(@RequestBody Course course);
}
