package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.abstraction.CatalogueService;
import com.turkcell.pair1.productservice.service.dto.request.AddProductToCatalogueRequest;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogues")
@RequiredArgsConstructor
public class CatalogueController {
    private final CatalogueService catalogueService;

    // Return a DTO?
    @PostMapping("/{catalogueId}/products")
    public Product addProductToCatalogue(
            @PathVariable Integer catalogueId,
            @RequestBody AddProductToCatalogueRequest request) {
        return catalogueService.addProductToCatalogue(catalogueId, request);
    }

    @GetMapping("/{catalogueId}/products")
    public List<ProductDtoResponse> getCatalogueProducts(@PathVariable Integer catalogueId) {
        return catalogueService.getCatalogueProducts(catalogueId);
    }
}
