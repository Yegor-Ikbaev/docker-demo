package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class Controller {

    @Value("${message}")
    private String message;

    @Autowired
    private DataEntityRepository dataEntityRepository;

    @GetMapping("/hello")
    public String test() {
        var data = dataEntityRepository.findAll().stream()
                .map(dataEntity -> dataEntity.getId() + "->" + dataEntity.getValue())
                .collect(Collectors.joining(","));
        return message + "-" + System.getenv("SPRING_PROFILES_ACTIVE") + " | data=[" + data + "]";
    }

    @PostConstruct
    public void init() {
        if ("dev".equals(System.getenv("SPRING_PROFILES_ACTIVE"))) {
            dataEntityRepository.save(DataEntity.builder().value("value-dev-1").build());
            dataEntityRepository.save(DataEntity.builder().value("value-dev-2").build());
        }
        if ("qa".equals(System.getenv("SPRING_PROFILES_ACTIVE"))) {
            dataEntityRepository.save(DataEntity.builder().value("value-qa-1").build());
            dataEntityRepository.save(DataEntity.builder().value("value-qa-2").build());
        }
    }
}
