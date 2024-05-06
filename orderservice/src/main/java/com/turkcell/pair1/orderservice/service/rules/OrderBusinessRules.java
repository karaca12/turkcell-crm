package com.turkcell.pair1.orderservice.service.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.orderservice.client.InvoiceServiceClient;
import com.turkcell.pair1.orderservice.model.Order;
import com.turkcell.pair1.orderservice.model.OrderItem;
import com.turkcell.pair1.orderservice.repository.OrderRepository;
import com.turkcell.pair1.orderservice.service.dto.response.AccountHasActiveProductsResponse;
import com.turkcell.pair1.orderservice.service.dto.response.AddOrderAddressResponse;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderBusinessRules {
    private final MessageService messageService;
    private final InvoiceServiceClient invoiceServiceClient;
    private final OrderRepository orderRepository;

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
        return invoiceServiceClient.checkIfAccountExistsAndGetAddress(accountNumber, addressId);
    }

    public Order getOrderFromOptional(Optional<Order> optionalOrder) {
        return optionalOrder.orElseThrow(() ->
                new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ORDER_FOUND)));
    }

    public boolean doesAccountHasActiveProduct(String accountNumber) {
        return accountHasActiveProducts(accountNumber).isHasActiveProducts();
    }

    public AccountHasActiveProductsResponse accountHasActiveProducts(String accountNo) {
        List<Order> orders = orderRepository.findByAccountNumber(accountNo);
        return new AccountHasActiveProductsResponse(orders.stream()
                .flatMap(order -> order.getItems().stream())
                .anyMatch(OrderItem::isActive));
    }

    public boolean checkWithAccountNumbersIfCustomerHasActiveProduct(List<String> accountNumbers) {
        boolean isActive = false;
        int i = 0;
        while (!isActive && i < accountNumbers.size()) {
            isActive = doesAccountHasActiveProduct(accountNumbers.get(i));
            i++;
        }
        return isActive;
    }
}
