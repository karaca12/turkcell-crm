package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Street;


public interface StreetService {
    Street findStreetByNameAndCityAndIsDeletedFalse(String street, String city);
}