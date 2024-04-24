package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.dto.request.ClearBasketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice/baskets")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearBasket(@RequestBody ClearBasketRequest request) {
        basketService.clearBasket(request.getAccountId());
    }
}