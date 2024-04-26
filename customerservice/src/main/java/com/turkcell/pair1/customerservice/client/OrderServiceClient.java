package com.turkcell.pair1.customerservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "orderservice", configuration = FeignClientConfiguration.class)
public interface OrderServiceClient {
    @GetMapping("/api/orders")
    String getCustomerIdByOrderId(@RequestParam("orderId") String orderId);
}
