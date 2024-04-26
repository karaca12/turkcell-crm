package com.turkcell.pair1.invoiceservice.service.dto.request;

import lombok.Getter;

@Getter
public class AddItemToBasketRequest {
    private Integer accountId;
    private Integer productId;
    private int quantity;
}