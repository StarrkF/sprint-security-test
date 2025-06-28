package com.example.springsecuritytest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }

    @PostMapping("/deneme")
    public String home2() {
        return "Hello World2!";
    }
}
