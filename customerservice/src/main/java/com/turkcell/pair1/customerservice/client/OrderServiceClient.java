package com.turkcell.pair1.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="orderservice")
public interface OrderServiceClient {
    @GetMapping("/api/orders")
    String getCustomerIdByOrderId(@RequestParam("orderId") String orderId);
}
