package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.Street;
import com.turkcell.pair1.customerservice.repository.StreetRepository;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;
    private final MessageService messageService;


    @Override
    public Street findStreetByNameAndCity(String street, String city) {

        return streetRepository.findByNameAndCity_Name(street,city).orElseThrow(()->new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_STREET_AND_CITY_FOUND_ERROR)));
    }
}
