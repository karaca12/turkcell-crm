package com.turkcell.pair1.orderservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequest {
    private String accountNumber;
    private List<AddOrderItemRequest> orderItems;
    private LocalDate serviceStartDate;
    private Integer accountAddressId;
}