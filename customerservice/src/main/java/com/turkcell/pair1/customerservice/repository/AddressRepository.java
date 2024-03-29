package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}