package com.turkcell.pair1.productservice.controller;

import com.turkcell.pair1.productservice.service.abstraction.CatalogueService;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogues")
@RequiredArgsConstructor
public class CatalogueController {
    private final CatalogueService catalogueService;


    @GetMapping("/{catalogueId}/products")
    public List<ProductDtoResponse> getCatalogueProducts(@PathVariable Integer catalogueId) {
        return catalogueService.getCatalogueProducts(catalogueId);
    }
}
