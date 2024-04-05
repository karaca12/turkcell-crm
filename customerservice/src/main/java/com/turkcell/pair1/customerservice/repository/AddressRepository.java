package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByCustomer_CustomerId(String customerId);
}