package com.turkcell.pair1.invoiceservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableSecurity
@EnableFeignClients
public class InvoiceserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceserviceApplication.class, args);
	}
}
