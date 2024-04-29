package com.turkcell.pair1.orderservice.client;

import com.turkcell.pair1.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "productservice",configuration = FeignClientConfiguration.class)
public interface ProductServiceClient {
    @GetMapping("/api/products/price/{productId}")
    double getProductPriceById(@PathVariable("productId") int productId);


}