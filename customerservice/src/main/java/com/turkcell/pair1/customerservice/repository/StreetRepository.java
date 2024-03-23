package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
    Optional<Street> findByNameAndCity_Name(String street, String city);
}