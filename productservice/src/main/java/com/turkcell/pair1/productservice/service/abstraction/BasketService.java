package com.turkcell.pair1.productservice.service.abstraction;

import com.turkcell.pair1.productservice.service.dto.request.AddProductToBasketRequest;

public interface BasketService {
    boolean addProductToBasket(AddProductToBasketRequest addProductToBasketRequest);
}
