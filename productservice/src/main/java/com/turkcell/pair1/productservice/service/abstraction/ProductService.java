package com.turkcell.pair1.productservice.service.abstraction;

import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;

public interface ProductService {
    boolean hasActiveProducts(String customerId); //CustomerNo or Id?
    void add(AddProductRequest productAddDto);
    GetAccountProductResponse getProductById(int id);

}
