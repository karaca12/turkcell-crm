package com.turkcell.pair1.orderservice.service.abstraction;

import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    String getCustomerIdByOrderId(String orderId);

    Order placeOrder(Order order);
    List<Order> findOrdersByAccountId(int id);
    GetOrderByIdResponse getOrderById(String orderId);
}
