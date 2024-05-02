package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.repository.BasketItemRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BasketItemServiceImpl implements BasketItemService {
    private final BasketItemRepository basketItemRepository;

    @Override
    public void clearAllItemsFromBasket(Basket basket) {
        basket.getBasketItems().stream().filter(basketItem -> !basketItem.isDeleted()).forEach(basketItem -> {
            basketItem.setDeleted(true);
            basketItem.setDeletedAt(LocalDateTime.now());
            basketItemRepository.save(basketItem);
        });
    }
}