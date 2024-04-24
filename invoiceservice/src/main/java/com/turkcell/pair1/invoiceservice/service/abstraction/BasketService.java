package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;

public interface BasketService {
    public BasketItem addBasketItem(Basket basket, Integer productId, int quantity);
    public void clearBasket(Integer accountId);
}
