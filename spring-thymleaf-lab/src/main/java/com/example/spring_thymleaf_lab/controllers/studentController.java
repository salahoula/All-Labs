package com.example.spring_thymleaf_lab.controllers;
import com.example.spring_thymleaf_lab.models.student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/students")
public class studentController {
    private List<student> students = new ArrayList<>();
    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", students);
        return "students/list";
    }
    @GetMapping("/register")
    public String showRegisterStudent(Model model) {
        model.addAttribute("student", new student()); // Correction ici
        return "students/register";
    }
    @PostMapping("/register")
    public String registerStudent(@Valid @ModelAttribute("student") student student,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "students/register";
        }
        students.add(student);
        return "redirect:/students/list";
    }
}
