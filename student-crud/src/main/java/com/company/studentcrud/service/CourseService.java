package com.company.studentcrud.service;

import com.company.studentcrud.dto.Course;
import com.company.studentcrud.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepo courseRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> findByStudentId(int id) {
        return courseRepo.findByStudentId(id);
    }
}
