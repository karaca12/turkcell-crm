package com.turkcell.pair1.customerservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair1.customerservice.service.dto.response.CustomerHasActiveProductsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "orderservice", configuration = FeignClientConfiguration.class)
public interface OrderServiceClient {
    @GetMapping("/api/orders/getCustomerIdByOrderId")
    String getCustomerIdByOrderId(@RequestParam("orderId") String orderId);

    @GetMapping("/api/orders/customer/{customerId}/hasActiveProducts")
    CustomerHasActiveProductsResponse customerHasActiveProducts(@PathVariable String customerId);
}
