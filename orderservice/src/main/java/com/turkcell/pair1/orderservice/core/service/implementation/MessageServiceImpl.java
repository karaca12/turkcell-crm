package com.turkcell.pair1.orderservice.core.service.implementation;

import com.turkcell.pair1.orderservice.core.service.abstraction.MessageService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    public MessageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessageWithArgs(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
