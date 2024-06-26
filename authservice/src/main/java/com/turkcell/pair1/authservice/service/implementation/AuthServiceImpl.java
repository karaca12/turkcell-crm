package com.turkcell.pair1.authservice.service.implementation;

import com.turkcell.pair1.authservice.service.abstraction.AuthService;
import com.turkcell.pair1.authservice.service.abstraction.UserService;
import com.turkcell.pair1.authservice.service.dto.request.LoginRequest;
import com.turkcell.pair1.authservice.service.dto.request.RegisterRequest;
import com.turkcell.pair1.authservice.service.rules.AuthBusinessRules;
import com.turkcell.pair1.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthBusinessRules businessRules;


    @Override
    public void register(RegisterRequest request) {
        userService.add(request);
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        businessRules.isAuthenticated(authentication);

        UserDetails user = userService.loadUserByUsername(request.getUsername());
        return jwtService.generateToken(user.getUsername(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }
}
