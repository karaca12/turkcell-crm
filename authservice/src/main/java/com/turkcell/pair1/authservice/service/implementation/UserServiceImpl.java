package com.turkcell.pair1.authservice.service.implementation;

import com.turkcell.pair1.authservice.entity.User;
import com.turkcell.pair1.authservice.repository.UserRepository;
import com.turkcell.pair1.authservice.service.abstraction.UserService;
import com.turkcell.pair1.authservice.service.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow();
    }


    @Override
    public void add(RegisterRequest request) {
        User user=new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        userRepository.save(user);
    }
}
