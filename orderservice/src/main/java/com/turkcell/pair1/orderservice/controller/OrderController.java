package com.turkcell.pair1.orderservice.controller;

import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/{orderId}")
    public Optional<Order> getOrderById(@PathVariable String orderId){
        return orderService.getOrderById(orderId);

    }

}
