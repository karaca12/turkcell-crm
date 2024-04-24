package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.pair1.invoiceservice.entity.Street;
import com.turkcell.pair1.invoiceservice.repository.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreetBusinessRules {
    private final StreetRepository streetRepository;

    public Street getStreetByNameAndCity(String street, String city) {
        return streetRepository.findByNameAndCity_Name(street,city).orElseThrow();
    }

}
