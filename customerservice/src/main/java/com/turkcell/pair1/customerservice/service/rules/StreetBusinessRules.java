package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.entity.Street;
import com.turkcell.pair1.customerservice.repository.StreetRepository;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreetBusinessRules {
    private final StreetRepository streetRepository;
    private final MessageService messageService;

    public Street getStreetByNameAndCity(String street, String city) {
        return streetRepository.findByNameAndCity_Name(street, city).orElseThrow(() ->
                new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_STREET_AND_CITY_FOUND_ERROR)));
    }
}