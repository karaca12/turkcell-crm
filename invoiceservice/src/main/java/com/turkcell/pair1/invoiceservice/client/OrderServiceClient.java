package com.turkcell.pair1.invoiceservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair1.invoiceservice.service.dto.response.AccountHasActiveProductsResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "orderservice", configuration = FeignClientConfiguration.class)
public interface OrderServiceClient {
    @GetMapping("/api/orders/account/{accountNumber}")
    List<GetAccountOrderResponse> findOrdersByAccountNumber(@PathVariable String accountNumber);

    @GetMapping("/api/orders/{orderId}")
    GetAccountOrderResponse getOrderById(@PathVariable String orderId);

    @GetMapping("/api/orders/account/{accountNo}/hasActiveProducts")
    AccountHasActiveProductsResponse accountHasActiveProducts(@PathVariable String accountNo);
}