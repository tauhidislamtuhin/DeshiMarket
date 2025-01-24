package com.example.deshimarket.controller;

import com.example.deshimarket.model.GigModel;
import com.example.deshimarket.model.GigModelDto;
import com.example.deshimarket.model.LogInModel;
import com.example.deshimarket.model.LogInModelDto;
import com.example.deshimarket.service.GigService;
import jakarta.servlet.http.HttpSession;
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
import java.util.List;
import java.util.Optional;

@Controller

public class GigController {
    private final GigService gigService;

    public GigController(GigService gigService) {
        this.gigService = gigService;
    }

    @RequestMapping("/gigs")
    public String listGigs(@RequestParam(required = false) String search, Model model) {

        List<GigModel> gigs = gigService.searchGigs(search);
        model.addAttribute("gigs", gigs);
        model.addAttribute("search", search);
        model.addAttribute("gig_size", gigs.size());
        return "gigs";
    }

/*
    @RequestMapping("/")
    public String home(@RequestParam(required = false) String search, Model model) {

        List<GigModel> gigs = gigService.searchGigs(search);
        model.addAttribute("gigs", gigs);
        model.addAttribute("search", search);
        model.addAttribute("gig_size", gigs.size());
        return "gigs";
    }
*/

    @GetMapping("/create-gig")
    public String showRegisterForm(Model model) {
        GigModelDto gigModelDto = new GigModelDto();
        model.addAttribute("gigModelDto", gigModelDto);
        return "create_gig";
    }

    @PostMapping("/create-gig")
    public String registerUser(@ModelAttribute GigModelDto gigModelDto, BindingResult bindingResult,HttpSession session) {

        MultipartFile image = gigModelDto.getGigImage();
        if (!image.isEmpty()) {
            Date date = new Date();
            String storageImageName = date.getTime() + "_" + image.getOriginalFilename();

            try {
                String imagePath = "public/gigs/";
                Path path = Paths.get(imagePath);

                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                }
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(imagePath + storageImageName), StandardCopyOption.REPLACE_EXISTING);

                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                int userId = (int) session.getAttribute("loggedInUserId");
                GigModel gigModel = new GigModel();
                gigModel.setTitle(gigModelDto.getTitle());
                gigModel.setDescription(gigModelDto.getDescription());
                gigModel.setPost_by(userId);
                gigModel.setKeywords(gigModelDto.getKeywords());
                gigModel.setPrice(gigModelDto.getPrice());
                gigModel.setPicture("gigs/"+storageImageName);

                /*logInModel.setName(logInModelDto.getName());
                logInModel.setUsername(logInModelDto.getUsername());
                logInModel.setEmail(logInModelDto.getEmail());
                logInModel.setPassword(logInModelDto.getPassword());
                logInModel.setRole(logInModelDto.getRole());
                logInModel.setAbout(logInModelDto.getAbout());
                logInModel.setPicture("profiles/"+storageImageName);
                System.out.println("Response :"+logInModel.toString());*/
                gigService.createGig(gigModel);

                return "redirect:/gigs";

            } catch (Exception e) {
                System.out.println("Error Response:"+e.getMessage());
            }
        }

        return "index";
    }



    @GetMapping("/gig/{id}")
    public String getGigDetails(@PathVariable int id, Model model) {
        Optional<GigModel> gig = gigService.findById(id);
        if (gig.isPresent()) {
            model.addAttribute("gig", gig.get());
            return "gig/gig_details";
        } else {
            return "redirect:/gigs"; // Redirect to gig list if not found
        }
    }

}
