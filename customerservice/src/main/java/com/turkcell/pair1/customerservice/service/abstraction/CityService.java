package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.City;

public interface CityService {
    City findCityByName(String city);
}
