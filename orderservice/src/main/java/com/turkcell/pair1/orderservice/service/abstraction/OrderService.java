package com.turkcell.pair1.orderservice.service.abstraction;

import com.turkcell.pair1.orderservice.entity.Order;

import java.util.List;

public interface OrderService {
    String getCustomerIdByOrderId(String orderId);

    Order placeOrder(Order order);
    List<Order> findOrdersByAccountId(int id);
}
