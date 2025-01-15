package com.example.deshimarket;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }
}
