package com.turkcell.pair1.invoiceservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetDetailedAccountProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice",configuration = FeignClientConfiguration.class)
public interface ProductServiceClient {
    @GetMapping("/api/products/{productId}")
    GetAccountProductResponse getProductById(@PathVariable("productId") int productId);

    @GetMapping("/api/products/productDetails/{productId}")
    GetDetailedAccountProductResponse getProductDetailById(@PathVariable("productId") int productId);
}