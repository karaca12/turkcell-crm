package com.turkcell.pair1.orderservice.controller;

import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping
    public String getCustomerIdByOrderId(@RequestParam String orderId) {
        return orderService.getCustomerIdByOrderId(orderId);
    }
}
