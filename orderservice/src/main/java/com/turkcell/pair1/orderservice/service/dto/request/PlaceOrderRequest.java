package com.turkcell.pair1.orderservice.service.dto.request;

import com.turkcell.pair1.orderservice.entity.Address;
import com.turkcell.pair1.orderservice.entity.OrderItem;
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
    private int accountId;
    private List<AddOrderItemRequest> orderItems;
    //TODO:customerId account id ile account tarafindan cekilecek.
    private String customerId;
    private double totalPrice;
    private LocalDate serviceStartDate;
    private AddServiceAddressRequest addressRequest;
}
