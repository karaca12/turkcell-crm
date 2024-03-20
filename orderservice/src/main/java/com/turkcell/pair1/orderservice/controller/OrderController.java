package com.turkcell.pair1.orderservice.controller;

import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public int getCustomerIdByOrderId(@RequestParam String orderId) {
        return orderService.getCustomerIdByOrderId(orderId);
    }
}
