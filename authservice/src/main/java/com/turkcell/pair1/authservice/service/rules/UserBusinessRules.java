package com.turkcell.pair1.authservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.authservice.entity.User;
import com.turkcell.pair1.authservice.repository.UserRepository;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserBusinessRules {
    private final MessageService messageService;
    private final UserRepository userRepository;

    public User getUserFromOptional(Optional<User> optionalUser) {
        return optionalUser.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_USER_FOUND)));
    }

    public void checkIfUserAlreadyExists(String username) {
        if (userRepository.existsByUsername(username)){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.USER_ALREADY_EXISTS));
        }
    }
}
