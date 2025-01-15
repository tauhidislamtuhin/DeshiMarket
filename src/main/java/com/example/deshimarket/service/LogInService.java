package com.example.deshimarket.service;

import com.example.deshimarket.model.LogInModel;
import com.example.deshimarket.repository.LogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogInService {
    @Autowired
    private LogInRepository logInRepository;


    public void registerUser(LogInModel logInModel) {
        if (logInRepository.findByEmail(logInModel.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        } else if (logInRepository.findByUsername(logInModel.getUsername()) != null) {
            throw new IllegalArgumentException("Email UserName exists");
        } else {
            logInRepository.save(logInModel);
        }

    }


    public LogInModel logIn(String email, String password) {
        LogInModel logInModel = logInRepository.findByEmail(email);
        if (logInModel != null && logInModel.getPassword().equals(password)) {
            return logInModel;
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }

    }


/*    public LogInModel findByUserName(String email) {
        return logInRepository.findByEmail(email);
    }

    public LogInModel Create(LogInModel logInModel) {
        return logInRepository.save(logInModel);
    }*/
}
