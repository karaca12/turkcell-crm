package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Street;
import com.turkcell.pair1.invoiceservice.service.abstraction.StreetService;
import com.turkcell.pair1.invoiceservice.service.rules.StreetBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreetServiceImpl implements StreetService {
    private final StreetBusinessRules businessRules;
    @Override
    public Street findStreetByNameAndCityAndIsDeletedFalse(String street, String city) {
        return businessRules.getStreetByNameAndCity(street, city);
    }
}
