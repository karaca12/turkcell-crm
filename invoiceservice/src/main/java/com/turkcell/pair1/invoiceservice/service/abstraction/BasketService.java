package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;

public interface BasketService {
    BasketItem addBasketItem(Basket basket, Integer productId, int quantity);
    void clearBasket(Integer accountId);

    Basket createBasket();
}