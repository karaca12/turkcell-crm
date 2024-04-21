package com.turkcell.pair1.authservice.controller;

import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final MessageService messageService;
    @GetMapping
    public String get() {
        return "Merhaba";
    }

    @PostMapping
    public String post() {
        return "Merhaba admin";
    }
}
