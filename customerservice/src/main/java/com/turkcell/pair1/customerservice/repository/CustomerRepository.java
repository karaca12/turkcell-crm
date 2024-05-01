package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByIsDeletedFalseAndCustomerId(String customerId);

    boolean existsByCustomerId(String customerId);

    @Modifying
    @Query("update Customer c set c.email = :#{#updateRequest.email}," +
            " c.fax = :#{#updateRequest.fax}," +
            " c.updatedAt=current timestamp " +
            "where c.id=:#{#id} and c.isDeleted=false ")
    void updateCustomerContactMediumById(Integer id, UpdateContactMediumRequest updateRequest);
}