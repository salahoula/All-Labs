package com.example.spring_thymleaf_lab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message",
                "Welcome to Spring Thymleaf Lab");
        return "home";
    }
}
