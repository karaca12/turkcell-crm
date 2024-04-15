package com.turkcell.pair1.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="productservice")
public interface ProductServiceClient {
    @GetMapping("/api/products/hasActiveProducts")
    boolean hasActiveProducts(@RequestParam("customerId") String customerId);
}
