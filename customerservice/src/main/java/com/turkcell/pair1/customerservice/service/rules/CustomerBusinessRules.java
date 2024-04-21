package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.client.ProductServiceClient;
import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.service.abstraction.MessageService;
import com.turkcell.pair1.service.constants.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final OrderServiceClient orderServiceClient;
    private final ProductServiceClient productServiceClient;

    public void customerWithSameNationalityIdCannotExist(String nationalityId) {
        if (customerRepository.existsByNationalityId(nationalityId)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DUPLICATE_NATIONALITY_ID_ERROR));
        }
    }

    public String getCustomerIdFromOrderNumber(String orderNumber) {
        if (orderNumber != null) {
            return orderServiceClient.getCustomerIdByOrderId(orderNumber);
        } else return null;
    }

    public Customer getCustomerFromOptional(Optional<Customer> optionalCustomer) {
        return optionalCustomer.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND)));
    }

    public void ensureCustomerHasNoActiveProducts(Customer customer) {
        //TODO: implement after product service is implemented
    }
}
