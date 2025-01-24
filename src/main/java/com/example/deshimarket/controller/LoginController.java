package com.example.deshimarket.controller;


import com.example.deshimarket.model.LogInModel;
import com.example.deshimarket.model.LogInModelDto;
import com.example.deshimarket.repository.LogInRepository;
import com.example.deshimarket.service.LogInService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private LogInService logInService;


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        LogInModelDto logInModelDto = new LogInModelDto();
        model.addAttribute("logInModelDto", logInModelDto);
        return "login/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute LogInModelDto logInModelDto, BindingResult bindingResult, HttpSession session) {
        MultipartFile image = logInModelDto.getImage();
        if (!image.isEmpty()) {
            Date date = new Date();
            String storageImageName = date.getTime() + "_" + image.getOriginalFilename();

            try {
                String imagePath = "public/profiles/";
                Path path = Paths.get(imagePath);

                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(imagePath + storageImageName), StandardCopyOption.REPLACE_EXISTING);

                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                LogInModel logInModel = new LogInModel();
                logInModel.setName(logInModelDto.getName());
                logInModel.setUsername(logInModelDto.getUsername());
                logInModel.setEmail(logInModelDto.getEmail());
                logInModel.setPassword(logInModelDto.getPassword());
                logInModel.setRole(logInModelDto.getRole());
                logInModel.setAbout(logInModelDto.getAbout());
                logInModel.setPicture("profiles/"+storageImageName);
                System.out.println("Response :"+logInModel.toString());
                logInService.registerUser(logInModel);
                session.setAttribute("loggedInUserId", logInModel.getId());

                return "redirect:/login";

            } catch (Exception e) {
                System.out.println("Error Response:"+e.getMessage());
            }
        }else {
            LogInModel logInModel = new LogInModel();
            logInModel.setName(logInModelDto.getName());
            logInModel.setUsername(logInModelDto.getUsername());
            logInModel.setEmail(logInModelDto.getEmail());
            logInModel.setPassword(logInModelDto.getPassword());
            logInModel.setRole(logInModelDto.getRole());
            logInModel.setAbout(logInModelDto.getAbout());
            logInModel.setPicture(null);
            System.out.println("index :"+logInModel.toString());
            logInService.registerUser(logInModel);


            return "redirect:/login";
        }

        return "index";
    }
/*    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("login", new LogInModel());
        return "register";
    }*/

/*
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") LogInModel login, Model model) {
        try {
           // LogInModel logInModel = new LogInModel();
            login.setPicture("picture");
         //   logInService.registerUser(login);
            model.addAttribute("message", "Registration successful!");

            System.out.println("Response :"+login.getEmail());
            model.addAttribute("login", login);
            System.out.println("Response :"+login.getPicture());
            return "index";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println("Error Response:"+e.getMessage());
        }
        return "index";
    }
*/

/*    @PostMapping("/register")
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
    }*/

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new LogInModel());
        return "login/login";
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("login", new LogInModel());
        return "login/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LogInModel user, Model model,HttpSession session) {
        try {
            LogInModel loggedInUser = logInService.logIn(user.getEmail(), user.getPassword());
            model.addAttribute("message", "Login successful!");
            model.addAttribute("user", loggedInUser);
            System.out.println("Response :"+loggedInUser.getRole());
            session.setAttribute("loggedInUserId", loggedInUser.getId());
            int userId = (int) session.getAttribute("loggedInUserId");
            System.out.println("loggedInUserId :"+userId);
            System.out.println(user.getEmail()+" Password:"+ loggedInUser.getPassword());
            if (loggedInUser.getRole().equals("Buyer")){
                return "redirect:/gigs";
            }else {
                return "/dashboard/dashboard";
            }

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println("Error Response:"+e.getMessage());
            return "index";
        }
    }

}