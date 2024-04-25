package com.turkcell.pair1.orderservice.service.implementation;

import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.message.Messages;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.repository.OrderRepository;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MessageService messageService;


    @Override
    public String getCustomerIdByOrderId(String orderId) {
        Order order=orderRepository.findById(orderId).orElseThrow(()->
                new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ORDER_FOUND)));

        return order.getCustomerId();
    }
}
