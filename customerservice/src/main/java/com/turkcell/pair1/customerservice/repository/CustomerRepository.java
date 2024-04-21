package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    @Query("select new com.turkcell.pair1.customerservice.service.dto.response." +
            "SearchCustomerResponse(c.customerId,c.firstName,c.middleName,c.lastName,c.nationalityId) " +
            "from Customer c " +
            "where (:#{#request.nationalityId} is null or c.nationalityId=:#{#request.nationalityId}) " +
            "and (:#{#request.customerId} is null or c.customerId=:#{#request.customerId})" +
            "and (:#{#request.accountNumber} is null or c.accountNumber=:#{#request.accountNumber})" +
            "and (:#{#request.mobilePhone} is null or c.mobilePhone=:#{#request.mobilePhone})" +
            "and (:#{#request.firstName} is null or c.firstName=:#{#request.firstName})" +
            "and (:#{#request.lastName} is null or c.lastName=:#{#request.lastName}) " +
            "and (:#{#customerId} is null or c.customerId=:#{#customerId}) " +
            "and c.isDeleted=false ")
    List<SearchCustomerResponse> search(@Param("request") SearchCustomerRequest request, String customerId, Pageable pageable);

    Optional<Customer> findByIsDeletedFalseAndCustomerId(String customerId);

    boolean existsByNationalityId(String nationalityId);

    @Transactional
    @Modifying
    @Query("update Customer c set c.firstName = :#{#updateRequest.firstName}, c.middleName = :#{#updateRequest.middleName}," +
            " c.lastName = :#{#updateRequest.lastName}, c.birthDate = :#{#updateRequest.birthDate}," +
            " c.gender = :#{#updateRequest.gender}, c.fatherName = :#{#updateRequest.fatherName}," +
            " c.motherName = :#{#updateRequest.motherName}, c.nationalityId = :#{#updateRequest.nationalityId}," +
            " c.updatedAt=current timestamp " +
            "where c.id=:#{#id} and c.isDeleted=false ")
    void updateCustomerInfoById(Integer id,@Param("updateRequest") UpdateCustomerInfoRequest updateRequest);

    @Transactional
    @Modifying
    @Query("update Customer c set c.email = :#{#updateRequest.email}, c.homePhone = :#{#updateRequest.homePhone}," +
            " c.mobilePhone = :#{#updateRequest.mobilePhone}, c.fax = :#{#updateRequest.fax}," +
            " c.updatedAt=current timestamp " +
            "where c.id=:#{#id} and c.isDeleted=false ")
    void updateCustomerContactMediumById(Integer id, UpdateContactMediumRequest updateRequest);
}