package com.company.studentcrud.service;

import com.company.studentcrud.dto.Student;
import com.company.studentcrud.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepo.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    public void updateStudent(Student student) {
        studentRepo.save(student);
    }
}
