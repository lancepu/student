package com.company.studentcrud.service;

import com.company.studentcrud.dto.Course;
import com.company.studentcrud.repo.CourseRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CourseServiceTest {
    private CourseRepo courseRepo = Mockito.mock(CourseRepo.class);
    private CourseService courseService;

    @Before
    public void setUp() {
        courseService = new CourseService(courseRepo);
    }

    @Test
    public void shouldAddCourse() {
        Course course = new Course();
        course.setName("Biology");
        course.setStudentId(1);
        course.setScore(99);

        Course fromDb = new Course();
        fromDb.setId(1);
        fromDb.setName("Biology");
        fromDb.setStudentId(1);
        fromDb.setScore(99);

        Mockito.when(courseRepo.save(course)).thenReturn(fromDb);

        assertEquals(fromDb, courseService.addCourse(course));
    }

    @Test
    public void shouldGetCourseByStudentId() {
        Course fromDb = new Course();
        fromDb.setId(1);
        fromDb.setName("Biology");
        fromDb.setStudentId(1);
        fromDb.setScore(99);
        List<Course> courses = Collections.singletonList(fromDb);
        Mockito.when(courseRepo.findByStudentId(1)).thenReturn(courses);

        assertEquals(courses, courseService.findByStudentId(1));
    }
}