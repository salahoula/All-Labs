package com.example.Student_api.service;
import com.example.Student_api.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Student_api.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);}
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();}
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with id: " + id));}
    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setMajor(studentDetails.getMajor());
        return studentRepository.save(student);}
    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);}
}
