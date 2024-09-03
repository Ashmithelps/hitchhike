package com.example.hitchhikking_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hitchhikking_app.model.User;
import com.example.hitchhikking_app.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle user registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, 
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
    
        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER"); 
        }
    
        try {
            userService.saveUser(user); // Assuming you use saveUser() method
            return "redirect:/users/success";
        } catch (RuntimeException e) { 
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }
    
    

    // Registration success page
    @GetMapping("/success")
    public String registrationSuccess(Model model) {
        model.addAttribute("message", "Registration successful!");
        return "success";
    }

    // Test page (remove or modify as needed)
    @GetMapping("/test")
    public String showTestPage() {
        return "test";
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}