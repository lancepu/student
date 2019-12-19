package com.company.studentedge.service;

import com.company.studentedge.feign.StudentClient;
import com.company.studentedge.model.Course;
import com.company.studentedge.model.Student;
import com.company.studentedge.model.StudentViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {

    private StudentClient studentClient = Mockito.mock(StudentClient.class);

    private StudentService studentService;

    @Before
    public void setUp() {
        studentService = new StudentService(studentClient);
    }

    @Test
    public void shouldAddStudent() {
        Student student = new Student();
        student.setFirstName("Lance");
        student.setLastName("Pu");

        Course course = new Course();
        course.setName("Biology");
        course.setScore(99);
        course.setStudentId(1);

        List<Course> courses = Collections.singletonList(course);

        StudentViewModel studentViewModel = new StudentViewModel();
        studentViewModel.setFirstName(student.getFirstName());
        studentViewModel.setLastName(student.getLastName());
        studentViewModel.setCourses(courses);

        Student studentFromDb = new Student();
        studentFromDb.setStudentId(1);
        studentFromDb.setFirstName("Lance");
        studentFromDb.setLastName("Pu");

        Course courseFromDb = new Course();
        courseFromDb.setId(1);
        courseFromDb.setName("Biology");
        courseFromDb.setScore(99);
        courseFromDb.setStudentId(1);

        List<Course> coursesFromDb = Collections.singletonList(courseFromDb);
        StudentViewModel result = new StudentViewModel();
        result.setId(1);
        result.setFirstName("Lance");
        result.setLastName("Pu");
        result.setCourses(coursesFromDb);

        Mockito.when(studentClient.addCourse(course)).thenReturn(courseFromDb);
        Mockito.when(studentClient.addStudent(student)).thenReturn(studentFromDb);

        assertEquals(result, studentService.addStudent(studentViewModel));


    }

    @Test
    public void shouldGetStudent() {
        Student studentFromDb = new Student();
        studentFromDb.setStudentId(1);
        studentFromDb.setFirstName("Lance");
        studentFromDb.setLastName("Pu");

        Course courseFromDb = new Course();
        courseFromDb.setId(1);
        courseFromDb.setName("Biology");
        courseFromDb.setScore(99);
        courseFromDb.setStudentId(1);

        List<Course> coursesFromDb = Collections.singletonList(courseFromDb);
        StudentViewModel result = new StudentViewModel();
        result.setId(1);
        result.setFirstName("Lance");
        result.setLastName("Pu");
        result.setCourses(coursesFromDb);

        Mockito.when(studentClient.getCoursesByStudentId(1)).thenReturn(coursesFromDb);
        Mockito.when(studentClient.getStudentById(1)).thenReturn(studentFromDb);

        assertEquals(result, studentService.getStudentById(1));
    }

}