package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @Query("select new com.turkcell.pair1.customerservice.service.dto.response." +
            "SearchCustomerResponse(c.customerId,c.firstName,c.middleName,c.lastName,c.nationalityId) " +
            "from Customer c " +
            "where (:#{#request.nationalityId} <=0  or c.nationalityId=:#{#request.nationalityId}) " +
            "and (:#{#request.customerId} is null or c.customerId=:#{#request.customerId})" +
            "and (:#{#request.accountNumber} is null or c.accountNumber=:#{#request.accountNumber})" +
            "and (:#{#request.mobilePhone} is null or c.mobilePhone=:#{#request.mobilePhone})" +
            "and (:#{#request.firstName} is null or c.firstName=:#{#request.firstName})" +
            "and (:#{#request.lastName} is null or c.lastName=:#{#request.lastName})")
    List<SearchCustomerResponse> search(@Param("request") SearchCustomerRequest request);

    Optional<Customer> findByCustomerId(String customerId);
}