package com.turkcell.pair1.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/test")
@RestController
public class TestController {
    @GetMapping
    public String get() {
        return "Merhaba";
    }

    @PostMapping
    public String post() {
        return "Merhaba admin";
    }
}
