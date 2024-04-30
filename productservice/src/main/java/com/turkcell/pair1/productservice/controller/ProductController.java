package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.request.ProductConfiguration;
import com.turkcell.pair1.productservice.service.dto.request.ProductConfigurationRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return productService.getAccountProductById(id);
    }
    @GetMapping("price/{id}")
    public double getProductPrice(@PathVariable("id") int id){
        return productService.getProductPriceById(id);
    }

    @GetMapping("/search")
    public List<ProductDtoResponse> searchProducts(
            @RequestParam(required = false) String productOfferId,
            @RequestParam(required = false) String productOfferName) {
        return productService.searchProducts(productOfferId, productOfferName);
    }

    @GetMapping("/productDetails/{productId}")
    public GetDetailedAccountProductResponse getDetailedProduct(@PathVariable int productId) {
        return productService.getDetailedProduct(productId);

    }
}