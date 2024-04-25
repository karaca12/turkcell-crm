package com.turkcell.pair1.invoiceservice.client;

import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice")
public interface ProductServiceClient {
    @GetMapping("/api/products/{productId}")
    GetAccountProductResponse getProductById(@PathVariable("productId") int productId);
}