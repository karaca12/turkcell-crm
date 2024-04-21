package com.turkcell.pair1.customerservice.core.configuration;

import com.turkcell.pair1.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
    private final BaseSecurityService baseSecurityService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCoreSecurity(http);
        http
                .authorizeHttpRequests(req -> req
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
