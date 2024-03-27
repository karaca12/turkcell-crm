package com.turkcell.pair1.orderservice.service.implementation;

import com.turkcell.pair1.orderservice.core.exception.type.BusinessException;
import com.turkcell.pair1.orderservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.orderservice.core.service.constants.Messages;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.repository.OrderRepository;
import com.turkcell.pair1.orderservice.service.abstraction.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MessageService messageService;


    @Override
    public int getCustomerIdByOrderId(String orderId) {
        Order order=orderRepository.findById(orderId).orElseThrow(()-> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ORDER_FOUND_ERROR)));

        return order.getCustomerId();
    }
}
