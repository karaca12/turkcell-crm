package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
}