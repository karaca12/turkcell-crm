package com.turkcell.pair1.productservice.service.dto.request;

import lombok.Getter;

@Getter
public class AddProductToBasketRequest {
    private Long accountId;   // Account ID to validate the account via AccountService
    private Integer productId; // Product ID to be added to the basket
    private Integer quantity;  // Quantity of the product to be added
}
