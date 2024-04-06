package com.turkcell.pair1.authservice.service.abstraction;

import com.turkcell.pair1.authservice.service.dto.request.LoginRequest;
import com.turkcell.pair1.authservice.service.dto.request.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    String login(LoginRequest request);
}
