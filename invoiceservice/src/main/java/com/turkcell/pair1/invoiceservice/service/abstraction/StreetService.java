package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Street;

public interface StreetService {
    Street findStreetByNameAndCityAndIsDeletedFalse(String street, String city);

}