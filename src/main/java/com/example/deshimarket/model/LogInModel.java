package com.example.deshimarket.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Value;

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
    @Column(nullable=false)
    private double balance =0.0;


}
