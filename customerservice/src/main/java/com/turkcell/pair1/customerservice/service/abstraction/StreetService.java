package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Street;

import java.util.Optional;

public interface StreetService {
    Street findStreetByNameAndCity(String street, String city);
}
