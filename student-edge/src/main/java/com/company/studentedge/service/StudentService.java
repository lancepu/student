package com.company.studentedge.service;

import com.company.studentedge.feign.StudentClient;
import com.company.studentedge.model.Course;
import com.company.studentedge.model.Student;
import com.company.studentedge.model.StudentViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentClient studentClient;


    public StudentService(StudentClient studentClient) {
        this.studentClient = studentClient;
    }

    public StudentViewModel addStudent(StudentViewModel studentViewModel) {
        Student student = new Student();
        student.setFirstName(studentViewModel.getFirstName());
        student.setLastName(studentViewModel.getLastName());
        student = studentClient.addStudent(student);

        List<Course> courses = new ArrayList<>();

        for (Course course : studentViewModel.getCourses()) {
            course.setStudentId(student.getStudentId());
            course = studentClient.addCourse(course);
            courses.add(course);
        }

        StudentViewModel result = new StudentViewModel();
        result.setCourses(courses);
        result.setId(student.getStudentId());
        result.setLastName(student.getLastName());
        result.setFirstName(student.getFirstName());

        return result;
    }

    public StudentViewModel getStudentById(int id) {
        return buildStudentViewModel(studentClient.getStudentById(id));
    }

    private StudentViewModel buildStudentViewModel(Student student) {
        StudentViewModel studentViewModel = new StudentViewModel();
        studentViewModel.setFirstName(student.getFirstName());
        studentViewModel.setLastName(student.getLastName());
        studentViewModel.setId(student.getStudentId());
        List<Course> courses = studentClient.getCoursesByStudentId(student.getStudentId());
        studentViewModel.setCourses(courses);
        return studentViewModel;
    }
}
