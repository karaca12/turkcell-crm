package com.turkcell.pair1.productservice.service.abstraction;

import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;

import java.util.List;

public interface ProductService {
    boolean hasActiveProducts(String customerId); //CustomerNo or Id?
    void add(AddProductRequest productAddDto);
    GetAccountProductResponse getProductById(int id);

    Product getProductById(Integer productId);
    List<ProductDtoResponse> getProductsByCatalogueId(Integer catalogueId);

    List<ProductDtoResponse> searchProducts(Long productOfferId, String productOfferName);

    void submitConfigurations();

    void configureProduct();
}
