package com.turkcell.pair1.orderservice.core.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Bean
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("messages");
        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleResolver.setDefaultLocale(new Locale("en"));
        return acceptHeaderLocaleResolver;
    }
}
