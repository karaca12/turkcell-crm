package com.turkcell.pair1.authservice.controller;

import com.turkcell.pair1.authservice.service.abstraction.AuthService;
import com.turkcell.pair1.authservice.service.dto.request.LoginRequest;
import com.turkcell.pair1.service.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public void register(RegisterRequest request) {
        authService.register(request);
    }
    @PostMapping("/login")
    public String login(LoginRequest request) {
        return authService.login(request);
    }
}
