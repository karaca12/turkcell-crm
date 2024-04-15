package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // Return a DTO?
    @GetMapping("/hasActiveProducts")
    public boolean hasActiveProducts(@RequestParam String customerId) {
        return productService.hasActiveProducts(customerId);
    }
}