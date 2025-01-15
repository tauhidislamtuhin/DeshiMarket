package com.example.deshimarket.controller;


import com.example.deshimarket.model.LogInModel;
import com.example.deshimarket.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private LogInService logInService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("login", new LogInModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") LogInModel login, Model model) {
        try {
            logInService.registerUser(login);
            model.addAttribute("message", "Registration successful!");
            System.out.println("Response :"+login.getEmail());
            model.addAttribute("login", login);
            return "welcome";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println("Error Response:"+e.getMessage());
        }
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new LogInModel());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LogInModel user, Model model) {
        try {
            LogInModel loggedInUser = logInService.logIn(user.getEmail(), user.getPassword());
            model.addAttribute("message", "Login successful!");
            model.addAttribute("login", loggedInUser);
            System.out.println("Response :"+loggedInUser);
            return "welcome";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println("Error Response:"+e.getMessage());
            return "index";
        }
    }
/*    @GetMapping("/userform")
    public String showUserForm(Model model) {
        model.addAttribute("user", new LogInModel());
        return "userForm";  // The name of the HTML template (userForm.html)
    }

    @PostMapping("/submituser")
    public String submitUserForm(@ModelAttribute("user") LogInModel user
                                 *//*@RequestParam("picture") MultipartFile picture*//*) {
        // Process the form data here (e.g., save the user and picture)

        // For demonstration, let's print the user details
        System.out.println("User Name: " + user.getName());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());
        System.out.println("About: " + user.getAbout());


        // Return the appropriate view after submission
        return "userFormSuccess";  // A success page (userFormSuccess.html)
    }*/
}