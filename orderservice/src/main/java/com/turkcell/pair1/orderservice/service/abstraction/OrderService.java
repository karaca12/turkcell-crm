package com.turkcell.pair1.orderservice.service.abstraction;

import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByAccountNumberResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;

import java.util.List;

public interface OrderService {
    String getCustomerIdByOrderId(String orderId);

    void placeOrder(PlaceOrderRequest request);

    List<GetOrderByAccountNumberResponse> findOrdersByAccountNumber(String accountNumber);

    GetOrderByIdResponse getOrderById(String orderId);
}