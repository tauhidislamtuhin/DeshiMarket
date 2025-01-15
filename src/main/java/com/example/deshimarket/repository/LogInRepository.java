package com.example.deshimarket.repository;

import com.example.deshimarket.model.LogInModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogInRepository extends JpaRepository<LogInModel, Integer> {
    LogInModel findByEmail(String email);
    LogInModel findByUsername(String username);
}
