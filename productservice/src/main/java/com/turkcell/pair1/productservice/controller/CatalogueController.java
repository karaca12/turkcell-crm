package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.service.abstraction.CatalogueService;
import com.turkcell.pair1.productservice.service.dto.response.CatalogueProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products/catalogues")
@RequiredArgsConstructor
public class CatalogueController {
    private final CatalogueService catalogueService;


    @GetMapping("/{catalogueId}/products")
    public List<CatalogueProductResponse> getCatalogueProducts(@PathVariable Integer catalogueId) {
        return catalogueService.getCatalogueProducts(catalogueId);
    }
}
