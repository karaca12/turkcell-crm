package com.turkcell.pair1.productservice.service.abstraction;

import com.turkcell.pair1.productservice.service.dto.response.CatalogueProductResponse;

import java.util.List;

public interface CatalogueService {

    List<CatalogueProductResponse> getCatalogueProducts(Integer catalogueId);
}
