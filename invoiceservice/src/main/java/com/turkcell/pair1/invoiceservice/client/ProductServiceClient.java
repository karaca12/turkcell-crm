package com.turkcell.pair1.invoiceservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetDetailedAccountProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice",configuration = FeignClientConfiguration.class)
public interface ProductServiceClient {
    @GetMapping("/api/products/{productOfferId}")
    GetAccountProductResponse getAccountProductByOfferId(@PathVariable String productOfferId);

    @GetMapping("/api/products/productDetails/{productOfferId}")
    GetDetailedAccountProductResponse getProductDetailById(@PathVariable String productOfferId);
}