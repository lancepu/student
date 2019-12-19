package com.company.studentcrud.service;

import com.company.studentcrud.dto.Student;
import com.company.studentcrud.repo.StudentRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class StudentServiceTest {

    private StudentRepo studentRepo = Mockito.mock(StudentRepo.class);
    private StudentService studentService;

    @Before
    public void setUp() {
        studentService = new StudentService(studentRepo);
    }

    @Test
    public void shouldAddStudent() {
        Student student = new Student();
        student.setFirstName("Lance");
        student.setFirstName("Pu");

        Student fromDb = new Student();
        fromDb.setStudentId(1);
        fromDb.setFirstName("Lance");
        fromDb.setFirstName("Pu");

        Mockito.when(studentRepo.save(student)).thenReturn(fromDb);

        assertEquals(fromDb, studentService.addStudent(student));
    }

    @Test
    public void shouldGetStudent() {
        Student fromDb = new Student();
        fromDb.setStudentId(1);
        fromDb.setFirstName("Lance");
        fromDb.setFirstName("Pu");

        List<Student> students = Collections.singletonList(fromDb);

        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.of(fromDb));
        Mockito.when(studentRepo.findAll()).thenReturn(students);

        assertEquals(Optional.of(fromDb), studentService.getStudentById(1));
        assertEquals(students, studentService.findAllStudents());
    }

}