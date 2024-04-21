package com.turkcell.pair1.productservice;

import com.turkcell.pair1.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSecurity
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

}
