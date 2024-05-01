package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByIsDeletedFalseAndCustomer(Customer customer, Pageable pageable);

    @Modifying
    @Query("update Address a set a.street = :#{#address.street}, a.flatNumber = :#{#address.flatNumber}," +
            " a.description = :#{#address.description}," +
            " a.updatedAt=current timestamp " +
            "where a.id = :#{#updatedId}")
    void updateAddressById(@Param("address") Address address,Integer updatedId);

    Optional<Address> findByIdAndIsDeletedFalse(Integer id);
}