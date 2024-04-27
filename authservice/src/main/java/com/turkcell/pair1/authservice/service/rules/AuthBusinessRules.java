package com.turkcell.pair1.authservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthBusinessRules {
    private final MessageService messageService;


    public void isAuthenticated(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.WRONG_USERNAME_OR_PASSWORD));
        }
    }
}
