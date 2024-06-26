package com.turkcell.pair1.authservice;

import com.turkcell.pair1.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSecurity
public class AuthserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthserviceApplication.class, args);
    }

}
