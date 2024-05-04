package com.turkcell.pair1.authservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.authservice.entity.User;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserBusinessRules {
    private final MessageService messageService;

    public User getUserFromOptional(Optional<User> optionalUser) {
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException(messageService.getMessage(Messages.BusinessErrors.NO_USER_FOUND)));
    }
}
