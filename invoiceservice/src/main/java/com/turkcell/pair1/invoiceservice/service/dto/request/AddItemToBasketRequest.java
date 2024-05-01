package com.turkcell.pair1.invoiceservice.service.dto.request;

import lombok.Getter;

@Getter
public class AddItemToBasketRequest {
    private String accountNumber;
    private String productOfferId;
    private int quantity;
}