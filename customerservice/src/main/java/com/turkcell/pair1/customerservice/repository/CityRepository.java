package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<City, Integer> {
}