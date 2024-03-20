package com.turkcell.pair1.orderservice.service.implementation;

import com.turkcell.pair1.orderservice.repository.OrderRepository;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
