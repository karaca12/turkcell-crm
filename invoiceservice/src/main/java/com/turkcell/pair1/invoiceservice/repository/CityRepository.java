package com.turkcell.pair1.invoiceservice.repository;

import com.turkcell.pair1.invoiceservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
