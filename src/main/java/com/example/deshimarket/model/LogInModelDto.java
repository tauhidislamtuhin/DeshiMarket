package com.example.deshimarket.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class LogInModelDto {

    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
    private String about;
    private MultipartFile image;
    private double balance =0.0;


}
