package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.repository.BasketRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketItemService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.rules.BasketBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final BasketItemService basketItemService;
    private final BasketBusinessRules businessRules;

    @Transactional
    public BasketItem addBasketItem(Basket basket, String productOfferId, int quantity) {
        businessRules.checkIfProductExists(productOfferId);
        BasketItem item = new BasketItem();
        item.setProductOfferId(productOfferId);
        item.setQuantity(quantity);
        item.setBasket(basket);
        basket.getBasketItems().add(item);
        basketRepository.save(basket);
        return item;
    }

    @Transactional
    public void clearBasket(Account account) {
        Basket basket = account.getBasket();
        basketItemService.clearAllItemsFromBasket(basket);
    }

    @Override
    public Basket createBasket() {
        Basket basket = new Basket();
        return basketRepository.save(basket);
    }
}