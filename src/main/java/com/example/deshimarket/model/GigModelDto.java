package com.example.deshimarket.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class GigModelDto {
    private String title;
    private String description;
    private int post_by;
    private String keywords;
    private double price;
    private MultipartFile gigImage;
}
