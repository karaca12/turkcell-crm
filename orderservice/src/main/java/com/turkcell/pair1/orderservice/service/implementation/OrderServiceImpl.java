package com.turkcell.pair1.orderservice.service.implementation;


import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.orderservice.client.ProductServiceClient;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.entity.OrderItem;
import com.turkcell.pair1.orderservice.entity.ProductSpec;
import com.turkcell.pair1.orderservice.repository.OrderRepository;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderItemResponse;
import com.turkcell.pair1.orderservice.service.mapper.OrderMapper;
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

    @Override
    public String getCustomerIdByOrderId(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ORDER_FOUND)));

        return order.getCustomerId();
    }

    @Override
    public void placeOrder(PlaceOrderRequest request) {
        Order order = OrderMapper.INSTANCE.getOrderFromAddRequest(request);

        // Map each order item and set the price
        List<OrderItem> orderItems = request.getOrderItems().stream().map(item -> {
            OrderItem orderItem = OrderMapper.INSTANCE.getOrderItemFromAddRequest(item);
            double price = productServiceClient.getProductPriceById(item.getProductId());
            orderItem.setPrice(price);
            ProductSpec spec = OrderMapper.INSTANCE.productSpecFromAddRequest(item.getProductSpec());
            spec.setSpecId(UUID.randomUUID().toString());  // Generate UUID here
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
}