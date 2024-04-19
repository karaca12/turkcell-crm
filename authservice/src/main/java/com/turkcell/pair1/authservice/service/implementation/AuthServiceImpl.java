package com.turkcell.pair1.authservice.service.implementation;

import com.turkcell.pair1.authservice.service.abstraction.AuthService;
import com.turkcell.pair1.authservice.service.dto.request.LoginRequest;
import com.turkcell.pair1.jwt.JwtService;
import com.turkcell.pair1.service.abstraction.MessageService;
import com.turkcell.pair1.service.abstraction.UserService;
import com.turkcell.pair1.service.constants.Messages;
import com.turkcell.pair1.service.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;


    @Override
    public void register(RegisterRequest request) {
        userService.add(request);
    }

    @Override
    public String login(LoginRequest request) {


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (!authentication.isAuthenticated()){
            throw new RuntimeException();
        }

        UserDetails user= userService.loadUserByUsername(request.getUsername());
        return jwtService.generateToken(user.getUsername());
    }
}
