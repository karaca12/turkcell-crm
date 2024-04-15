package com.turkcell.pair1.productservice.service.implementation;

import com.turkcell.pair1.productservice.service.abstraction.ProductService;

public class ProductServiceImpl implements ProductService {
    @Override
    public boolean hasActiveProducts(String customerId) {
        return false; // No Active Products -> Safe to Delete Customer
    }
}
