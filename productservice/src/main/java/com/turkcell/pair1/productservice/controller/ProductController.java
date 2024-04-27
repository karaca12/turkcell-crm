package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
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

    @GetMapping("/search")
    public List<ProductDtoResponse> searchProducts(
            @RequestParam(required = false) Long productOfferId,
            @RequestParam(required = false) String productOfferName) {
        return productService.searchProducts(productOfferId, productOfferName);
    }

    @GetMapping("/configure") //TODO ??
    public void configureProduct(/*@RequestBody ConfigureRequest configureRequest*/) {
        productService.configureProduct();
    }

    @PostMapping("/submitConfigurations")
    public void submitConfigurations() {
        productService.submitConfigurations();
    }
    @GetMapping("/productDetails/{productId}")
    public GetDetailedAccountProductResponse getDetailedProduct(@PathVariable int productId) {
        return productService.getDetailedProduct(productId);

    }
}