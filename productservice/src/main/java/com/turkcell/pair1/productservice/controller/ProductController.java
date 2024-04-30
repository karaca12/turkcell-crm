package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.core.business.paging.PageInfo;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.request.SearchProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import com.turkcell.pair1.productservice.service.dto.response.SearchProductResponse;
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

    @GetMapping("/{id}")
    public GetAccountProductResponse getProduct(@PathVariable int id) {
        return productService.getAccountProductById(id);
    }
    @GetMapping("price/{productOfferId}")
    public double getProductPriceByOfferId(@PathVariable String productOfferId){
        return productService.getProductPriceByOfferId(productOfferId);
    }

    @GetMapping("/search")
    public List<SearchProductResponse> searchProducts(@RequestBody SearchProductRequest request,
                                                      @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return productService.searchProducts(request,pageInfo);
    }

    @GetMapping("/productDetails/{productId}")
    public GetDetailedAccountProductResponse getDetailedProduct(@PathVariable int productId) {
        return productService.getDetailedProduct(productId);

    }
}