package com.skyfall.tutorial.springbootapplication.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${welocme.message}")
    private String message;

    @GetMapping("/echo")
    public String echo() {
        return message;
    }
}
