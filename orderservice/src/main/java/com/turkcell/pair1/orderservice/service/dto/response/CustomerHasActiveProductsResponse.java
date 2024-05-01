package com.turkcell.pair1.orderservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerHasActiveProductsResponse {
    private boolean hasActiveProducts;
}
