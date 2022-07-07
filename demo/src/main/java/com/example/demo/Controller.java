package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Value("${message}")
    private String message;

    @GetMapping("/hello")
    public String test() {
        return message + "-" + System.getenv("SPRING_PROFILES_ACTIVE");
    }
}
