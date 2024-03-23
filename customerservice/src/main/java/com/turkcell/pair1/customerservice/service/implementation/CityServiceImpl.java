package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.City;
import com.turkcell.pair1.customerservice.repository.CityRepository;
import com.turkcell.pair1.customerservice.service.abstraction.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findCityByName(String city) {
        return cityRepository.findFirstByNameOrderByNameAsc(city).orElseThrow();
    }
}
