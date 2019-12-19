package com.company.studentedge.controller;

import com.company.studentedge.model.Course;
import com.company.studentedge.model.Student;
import com.company.studentedge.model.StudentViewModel;
import com.company.studentedge.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;
    @Autowired
    private StudentController studentController;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldAddStudent() throws Exception {
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

        Mockito.when(studentService.addStudent(studentViewModel)).thenReturn(result);

        String inputString = objectMapper.writeValueAsString(studentViewModel);
        String outputString = objectMapper.writeValueAsString(result);

        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(inputString))
                .andDo(print())
                .andExpect(content().json(outputString));
    }
    

}