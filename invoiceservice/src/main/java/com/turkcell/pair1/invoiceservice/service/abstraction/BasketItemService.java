package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Basket;

public interface BasketItemService {
    void clearAllItemsFromBasket(Basket basket);
}