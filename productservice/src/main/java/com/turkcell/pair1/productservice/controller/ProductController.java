package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.core.business.paging.PageInfo;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.SearchProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.SearchProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productOfferId}")
    public GetAccountProductResponse getAccountProductByOfferId(@PathVariable String productOfferId) {
        return productService.getAccountProductByOfferId(productOfferId);
    }
    @GetMapping("price/{productOfferId}")
    public double getProductPriceByOfferId(@PathVariable String productOfferId){
        return productService.getProductPriceByOfferId(productOfferId);
    }

    @PostMapping("/search")
    public List<SearchProductResponse> searchProducts(@RequestBody SearchProductRequest request,
                                                      @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return productService.searchProducts(request,pageInfo);
    }

    @GetMapping("/productDetails/{productOfferId}")
    public GetDetailedAccountProductResponse getProductDetailById(@PathVariable String productOfferId) {
        return productService.getDetailedProduct(productOfferId);

    }
}