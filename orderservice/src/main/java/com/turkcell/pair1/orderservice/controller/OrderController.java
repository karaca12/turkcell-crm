package com.turkcell.pair1.orderservice.controller;

import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByAccountNumberResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("getCustomerIdByOrderId")
    public String getCustomerIdByOrderId(@RequestParam String orderId) {
        return orderService.getCustomerIdByOrderId(orderId);
    }

    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody PlaceOrderRequest order) {
        orderService.placeOrder(order);
    }

    @GetMapping("/account/{accountNumber}")
    public List<GetOrderByAccountNumberResponse> getOrdersByAccountNumber(@PathVariable String accountNumber) {
        return orderService.findOrdersByAccountNumber(accountNumber);
    }

    @GetMapping("/{orderId}")
    public GetOrderByIdResponse getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }
}