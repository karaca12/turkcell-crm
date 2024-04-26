package com.turkcell.pair1.productservice.service.abstraction;

import com.turkcell.pair1.productservice.entity.Catalogue;
import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import com.turkcell.pair1.productservice.service.dto.request.AddProductToCatalogueRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CatalogueService {
    @Transactional
    Catalogue createOrUpdateCatalogue(Catalogue catalogue);

    @Transactional
    Product addProductToCatalogue(Integer catalogueId, AddProductToCatalogueRequest addProductToCatalogueRequest);

    List<ProductDtoResponse> getCatalogueProducts(Integer catalogueId);
}
