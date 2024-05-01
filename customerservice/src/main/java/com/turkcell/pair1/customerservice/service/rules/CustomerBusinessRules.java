package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;

    private final Random random = new Random();

    public String generateCustomerId() {
        String customerId;
        customerId = generateUniqueCustomerId();
        if (!isUniqueId(customerId)) {
            return customerId;
        } else {
            return generateCustomerId();
        }
    }

    private String generateUniqueCustomerId() {
        StringBuilder customerIdBuilder = new StringBuilder();
        customerIdBuilder.append(random.nextInt(5) + 1);
        for (int i = 0; i < 6; i++) {
            customerIdBuilder.append(random.nextInt(10));
        }
        return customerIdBuilder.toString();
    }

    private boolean isUniqueId(String customerId) {
        return customerRepository.existsByCustomerId(customerId);
    }

    public Customer getCustomerFromOptional(Optional<Customer> optionalCustomer) {
        return optionalCustomer.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND)));
    }
}