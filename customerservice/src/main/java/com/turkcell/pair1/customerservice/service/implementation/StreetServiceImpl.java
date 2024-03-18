package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.repository.StreetRepository;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import org.springframework.stereotype.Service;

@Service
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;

    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }
}
