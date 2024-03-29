package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Integer> {
    Optional<Street> findByNameAndCity_Name(String street, String city);
}