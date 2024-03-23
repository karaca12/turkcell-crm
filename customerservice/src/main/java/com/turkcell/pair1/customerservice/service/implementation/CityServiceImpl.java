package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.City;
import com.turkcell.pair1.customerservice.repository.CityRepository;
import com.turkcell.pair1.customerservice.service.abstraction.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final MessageService messageService;

    public CityServiceImpl(CityRepository cityRepository, MessageService messageService) {
        this.cityRepository = cityRepository;
        this.messageService = messageService;
    }

    @Override
    public City findCityByName(String city) {
        return cityRepository.findFirstByNameOrderByNameAsc(city).orElseThrow(()->new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CITY_FOUND_ERROR)));
    }
}
