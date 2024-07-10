package com.josh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/")
    public String homeController() {
        return "Welcome, create your own social media app using my API. Endpoint: https://github.com/TheSachinDhakad/spring-boot-app/blob/main/README.md";
    }
}
