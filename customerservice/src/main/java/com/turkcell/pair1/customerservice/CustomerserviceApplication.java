package com.turkcell.pair1.customerservice;

import com.turkcell.pair1.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableSecurity
public class CustomerserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerserviceApplication.class, args);
    }

}
