package com.turkcell.pair1.orderservice.service.implementation;


import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.orderservice.client.ProductServiceClient;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.entity.OrderItem;
import com.turkcell.pair1.orderservice.entity.ProductSpec;
import com.turkcell.pair1.orderservice.repository.OrderRepository;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import com.turkcell.pair1.orderservice.service.dto.response.*;
import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.mapper.OrderMapper;
import com.turkcell.pair1.orderservice.service.rules.OrderBusinessRules;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MessageService messageService;
    private final ProductServiceClient productServiceClient;
    private final OrderBusinessRules businessRules;

    @Override
    public String getCustomerIdByOrderId(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ORDER_FOUND)));

        return order.getCustomerId();
    }

    @Override
    public void placeOrder(PlaceOrderRequest request) {
        businessRules.checkIfCustomerExistsByCustomerId(request.getCustomerId());
        AddOrderAddressResponse addressResponse= businessRules.checkIfAccountExistsAndGetAddress(request.getAccountNumber(),request.getAccountAddressId());
        Order order = OrderMapper.INSTANCE.getOrderFromAddRequest(request);
        order.setServiceAddress(OrderMapper.INSTANCE.getAddressFromAddressResponse(addressResponse));

        List<OrderItem> orderItems = request.getOrderItems().stream().map(item -> {
            OrderItem orderItem = OrderMapper.INSTANCE.getOrderItemFromAddRequest(item);
            double price = productServiceClient.getProductPriceByOfferId(item.getProductOfferId());
            orderItem.setPrice(price);
            orderItem.setActive(true);
            ProductSpec spec = OrderMapper.INSTANCE.productSpecFromAddRequest(item.getProductSpec());
            businessRules.checkIfSpecsIsJson(spec.getSpecs());
            spec.setSpecId(UUID.randomUUID().toString());
            orderItem.setProductSpec(spec);
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        order.setTotalPrice(orderItems.stream().mapToDouble(OrderItem::getPrice).sum());
        orderRepository.save(order);
    }

    @Override
    public List<GetOrderByIdResponse> findOrdersByAccountNumber(String accountNumber) {
        List<Order> orders=orderRepository.findByAccountNumber(accountNumber);
        List<GetOrderByIdResponse> orderByAccountNumberResponses=new ArrayList<>();
        for(Order order : orders){
            GetOrderByIdResponse getOrderByIdResponse = OrderMapper.INSTANCE.getOrderByIdResponseFromOrder(order);
            List<GetOrderItemResponse> getOrderItemResponses = OrderMapper.INSTANCE.getOrderItemListResponseFromOrderItem(order.getItems());
            getOrderByIdResponse.setOrderItems(getOrderItemResponses);
            orderByAccountNumberResponses.add(getOrderByIdResponse);

        }
        return orderByAccountNumberResponses;
    }

    @Override
    public GetOrderByIdResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("No order Found"));
        GetOrderByIdResponse getOrderByIdResponse = OrderMapper.INSTANCE.getOrderByIdResponseFromOrder(order);
        List<GetOrderItemResponse> getOrderItemResponses = OrderMapper.INSTANCE.getOrderItemListResponseFromOrderItem(order.getItems());
        getOrderByIdResponse.setOrderItems(getOrderItemResponses);
        return getOrderByIdResponse;
    }

    @Override
    public CustomerHasActiveProductsResponse customerHasActiveProducts(String customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId); // Assuming such a method exists
        return new CustomerHasActiveProductsResponse(orders.stream()
                .flatMap(order -> order.getItems().stream())
                .anyMatch(OrderItem::isActive));
    }
}