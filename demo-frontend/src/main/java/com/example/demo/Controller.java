package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Value("${message}")
    private String message;

    @GetMapping("/hello")
    public String test() {
        return new RestTemplate().getForObject("http://demo:8080/hello", String.class) + " | Frontend message: " + message;
    }
}
