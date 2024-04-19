package com.turkcell.pair1.authservice.core.config;

import com.turkcell.pair1.configuration.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;



@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
    private final BaseSecurityService baseSecurityService;

    private static final String[] WHITELIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/v1/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCoreSecurity(http
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITELIST_URLS).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/test/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/test/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                        .anyRequest().authenticated()
                ));

        return http.build();
    }
}
