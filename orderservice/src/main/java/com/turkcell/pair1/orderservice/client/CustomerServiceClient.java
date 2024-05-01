package com.turkcell.pair1.orderservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice", configuration = FeignClientConfiguration.class)
public interface CustomerServiceClient {
    @GetMapping("/api/customers/checkByCustomerIdIfCustomerExists/{customerId}")
    boolean checkByCustomerIdIfCustomerExists(@PathVariable String customerId);
}
