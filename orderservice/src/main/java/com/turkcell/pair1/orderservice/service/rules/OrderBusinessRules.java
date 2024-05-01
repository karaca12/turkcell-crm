package com.turkcell.pair1.orderservice.service.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.orderservice.client.CustomerServiceClient;
import com.turkcell.pair1.orderservice.client.InvoiceServiceClient;
import com.turkcell.pair1.orderservice.entity.Order;
import com.turkcell.pair1.orderservice.service.dto.response.AddOrderAddressResponse;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderBusinessRules {
    private final MessageService messageService;
    private final InvoiceServiceClient invoiceServiceClient;
    private final CustomerServiceClient customerServiceClient;

    public boolean checkIfSpecsIsJson(String specs) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readTree(specs);
            return true;
        } catch (JsonProcessingException e) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.SPECS_IS_NOT_JSON));
        }

    }

    public AddOrderAddressResponse checkIfAccountExistsAndGetAddress(String accountNumber, Integer addressId) {
        return invoiceServiceClient.checkIfAccountExistsAndGetAddress(accountNumber,addressId);
    }

    public Order getOrderFromOptional(Optional<Order> optionalOrder) {
        return optionalOrder.orElseThrow(() ->
                new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ORDER_FOUND)));
    }
}
