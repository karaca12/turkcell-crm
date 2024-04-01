package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final OrderServiceClient orderServiceClient;

    public void customerWithSameNationalityIdCannotExist(String nationalityId) {
        if (customerRepository.existsByNationalityId(nationalityId)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DUPLICATE_NATIONALITY_ID_ERROR));
        }
    }

    public String getCustomerIdFromOrderNumber(String orderNumber) {
        if (orderNumber != null) {
            return orderServiceClient.getCustomerIdByOrderId(orderNumber);
        }
        else return null;
    }
}
