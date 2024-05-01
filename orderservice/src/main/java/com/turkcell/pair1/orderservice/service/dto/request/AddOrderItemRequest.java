package com.turkcell.pair1.orderservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderItemRequest {
    private String productOfferId;
    private AddProductSpecRequest productSpec;
}
