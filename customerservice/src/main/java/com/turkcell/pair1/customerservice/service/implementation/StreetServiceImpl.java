package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.Street;
import com.turkcell.pair1.customerservice.repository.StreetRepository;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;

    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    @Override
    public Street findStreetByNameAndCity(String street, String city) {

        return streetRepository.findByNameAndCity_Name(street,city).orElseThrow();
    }
}
