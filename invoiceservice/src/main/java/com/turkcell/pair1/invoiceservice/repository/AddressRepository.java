package com.turkcell.pair1.invoiceservice.repository;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByIsDeletedFalseAndAccounts(Account account);

    @Modifying
    @Query("update Address a set a.street = :#{#address.street}, a.flatNumber = :#{#address.flatNumber}," +
            " a.description = :#{#address.description}," +
            " a.updatedAt=current timestamp " +
            "where a.id = :#{#updatedId}")
    void updateAddressById(@Param("address") Address address,Integer updatedId);

}