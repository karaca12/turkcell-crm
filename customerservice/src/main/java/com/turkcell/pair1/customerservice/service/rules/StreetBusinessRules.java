package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.Street;
import com.turkcell.pair1.customerservice.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreetBusinessRules {
    private final StreetRepository streetRepository;
    private final MessageService messageService;

    public Street getStreetByNameAndCity(String street, String city) {
        return streetRepository.findByNameAndCity_Name(street,city).orElseThrow(()->
                new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_STREET_AND_CITY_FOUND_ERROR)));
    }
}
