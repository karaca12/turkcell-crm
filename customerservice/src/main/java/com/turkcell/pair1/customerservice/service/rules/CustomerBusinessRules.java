package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;

    public CustomerBusinessRules(CustomerRepository customerRepository, MessageService messageService) {
        this.customerRepository = customerRepository;
        this.messageService = messageService;
    }

    public void customerWithSameNationalityIdCannotExist(Integer nationalityId) {
        if (customerRepository.existsByNationalityId(nationalityId)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.DUPLICATE_NATIONALITY_ID_ERROR));
        }
    }
}
