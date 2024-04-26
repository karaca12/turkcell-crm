package com.turkcell.pair1.orderservice.controller;

import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping
    public String getCustomerIdByOrderId(@RequestParam String orderId) {
        return orderService.getCustomerIdByOrderId(orderId);
    }
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);


    }
    @GetMapping("/account/{accountId}")
    public List<Order> getOrdersByAccountId(@PathVariable int accountId) {
        return orderService.findOrdersByAccountId(accountId);
    }

}
