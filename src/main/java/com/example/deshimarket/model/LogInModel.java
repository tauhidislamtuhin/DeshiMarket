package com.example.deshimarket.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LOGIN_TABLE")
@Data
public class LogInModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable=false, unique=true)
    private String username;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private String role;
    private String about;
    private String picture;


}
