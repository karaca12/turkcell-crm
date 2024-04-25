package com.turkcell.pair1.customerservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="productservice",configuration = FeignClientConfiguration.class)
public interface ProductServiceClient {
    @GetMapping("/api/products/hasActiveProducts")
    boolean hasActiveProducts(@RequestParam("customerId") String customerId);
}
