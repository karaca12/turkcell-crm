package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.repository.BasketRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;

    @Transactional
    public BasketItem addBasketItem(Basket basket, Integer productId, int quantity) {
        BasketItem item = new BasketItem();
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setBasket(basket);
        basket.getBasketItems().add(item);
        basketRepository.save(basket);
        return item;
    }

    @Transactional
    public void clearBasket(Integer accountId) {
//        Account account = accountService.getAccountById(accountId).orElseThrow(/* TODO: IMPLEMENT*/);
//        Basket basket = account.getBasket();
//        if (basket != null) {
//            basket.getBasketItems().clear();
//            basketRepository.save(basket);
//        }
    }
}
