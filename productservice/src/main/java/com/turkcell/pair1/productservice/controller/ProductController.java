package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public void addProduct(@RequestBody AddProductRequest productdto) {

        productService.add(productdto);
    }

    @GetMapping("/{id}")
    public GetAccountProductResponse getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }
}