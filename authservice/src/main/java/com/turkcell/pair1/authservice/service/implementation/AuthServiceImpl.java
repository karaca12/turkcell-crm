package com.turkcell.pair1.authservice.service.implementation;

import com.turkcell.pair1.authservice.service.abstraction.AuthService;
import com.turkcell.pair1.authservice.service.abstraction.UserService;
import com.turkcell.pair1.authservice.service.dto.request.LoginRequest;
import com.turkcell.pair1.authservice.service.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    @Override
    public void register(RegisterRequest request) {
        userService.add(request);
    }

    @Override
    public void login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
    }
}
