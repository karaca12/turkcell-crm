package com.turkcell.pair1.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String productOfferId;
    private double price;
    private ProductSpec productSpec;
    private boolean isActive;
}