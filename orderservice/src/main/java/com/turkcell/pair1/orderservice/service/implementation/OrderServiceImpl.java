package com.turkcell.pair1.orderservice.service.implementation;


import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.orderservice.client.ProductServiceClient;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.entity.OrderItem;
import com.turkcell.pair1.orderservice.repository.OrderRepository;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import com.turkcell.pair1.orderservice.service.dto.request.AddOrderItemRequest;
import com.turkcell.pair1.orderservice.service.dto.request.PlaceOrderRequest;
import com.turkcell.pair1.orderservice.service.dto.response.GetOrderByIdResponse;
import com.turkcell.pair1.orderservice.service.mapper.OrderMapper;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Order order=OrderMapper.INSTANCE.getOrderFromAddRequest(request);
        order.setServiceAddress(OrderMapper.INSTANCE.getAddressFromAddAddressRequest(request.getAddressRequest()));

        List<OrderItem> orderItems=new ArrayList<>();
        double totalPrice = 0.0;
        for (AddOrderItemRequest item : request.getOrderItems()) {
            double price = productServiceClient.getProductPriceById(item.getProductId());
            OrderItem orderitem=new OrderItem(item.getProductId(), price);
            orderItems.add(orderitem);
            totalPrice += price;
        }
        order.setItems(orderItems);

        order.setTotalPrice(totalPrice);


        orderRepository.save(order);
    }

    @Override
    public List<GetOrderByIdResponse> findOrdersByAccountId(int accountId) {


        return OrderMapper.INSTANCE.getOrderByIdResponseListFromOrderList(orderRepository.findByAccountId(accountId));
    }

    @Override
    public GetOrderByIdResponse getOrderById(String orderId) {

        return OrderMapper.INSTANCE.getOrderByIdResponseFromOrder(orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("No order Found")));
    }
}
