package com.turkcell.pair1.productservice.service.abstraction;

import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;

import java.util.List;

public interface CatalogueService {

    List<ProductDtoResponse> getCatalogueProducts(Integer catalogueId);
}
