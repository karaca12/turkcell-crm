package com.turkcell.pair1.authservice.service.implementation;

import com.turkcell.pair1.authservice.entity.User;
import com.turkcell.pair1.authservice.repository.UserRepository;
import com.turkcell.pair1.authservice.service.abstraction.UserService;
import com.turkcell.pair1.authservice.service.dto.request.RegisterRequest;
import com.turkcell.pair1.authservice.service.rules.UserBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserBusinessRules userBusinessRules;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userBusinessRules.getUserFromOptional(userRepository.findByUsername(username));
    }

    @Override
    public void add(RegisterRequest request) {
        userBusinessRules.checkIfUserAlreadyExists(request.getUsername());
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

}
