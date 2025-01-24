package com.example.deshimarket.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "GIG_TABLE")
@Data
public class GigModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int post_by;
    private String keywords;
    private double price;
    @Column(nullable = false)
    private String picture;

}
