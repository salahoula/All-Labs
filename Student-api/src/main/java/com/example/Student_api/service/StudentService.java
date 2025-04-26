package com.example.Student_api.service;

import java.util.List;
import com.example.Student_api.model.Student;
public interface StudentService {
    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}
