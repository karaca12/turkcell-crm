package com.turkcell.pair1.orderservice.service.abstraction;

import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.dto.response.AccountHasActiveProductsResponse;
import com.turkcell.pair1.orderservice.service.dto.response.CustomerHasActiveProductsResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;

import java.util.List;

public interface OrderService {
    String getAccountNumberByOrderId(String orderId);

    void placeOrder(PlaceOrderRequest request);

    List<GetOrderByIdResponse> findOrdersByAccountNumber(String accountNumber);

    GetOrderByIdResponse getOrderById(String orderId);

    CustomerHasActiveProductsResponse customerHasActiveProducts(String customerId);

    AccountHasActiveProductsResponse accountHasActiveProducts(String accountNo);
}