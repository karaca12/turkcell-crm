package com.turkcell.pair1.customerservice.repository;

import com.turkcell.pair1.customerservice.entity.IndividualCustomer;
import com.turkcell.pair1.customerservice.service.dto.request.SearchIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateIndividualCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.SearchIndividualCustomerResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer> {


    @Query("select new com.turkcell.pair1.customerservice.service.dto.response." +
            "SearchIndividualCustomerResponse(c.customerId,c.individualCustomer.firstName,c.individualCustomer.middleName,c.individualCustomer.lastName,c.individualCustomer.nationalityId) " +
            "from Customer c " +
            "where (:#{#request.nationalityId} is null or c.individualCustomer.nationalityId like %:#{#request.nationalityId}%) " +
            "and (:#{#request.customerId} is null or c.customerId like %:#{#request.customerId}%)" +
            "and (:#{#request.mobilePhone} is null or c.individualCustomer.mobilePhone like %:#{#request.mobilePhone}%)" +
            "and (:#{#request.firstName} is null or c.individualCustomer.firstName like %:#{#request.firstName}%)" +
            "and (:#{#request.lastName} is null or c.individualCustomer.lastName like %:#{#request.lastName}%) " +
            "and (:#{#customerId} is null or c.customerId like %:#{#customerId}%) " +
            "and c.isDeleted=false ")
    List<SearchIndividualCustomerResponse> search(@Param("request") SearchIndividualCustomerRequest request, String customerId, Pageable pageable);

    boolean existsByNationalityId(String nationalityId);

    @Modifying
    @Query("update IndividualCustomer ic set ic.firstName = :#{#updateRequest.firstName}, ic.middleName = :#{#updateRequest.middleName}," +
            " ic.lastName = :#{#updateRequest.lastName}, ic.birthDate = :#{#updateRequest.birthDate}," +
            " ic.gender = :#{#updateRequest.gender}, ic.fatherName = :#{#updateRequest.fatherName}," +
            " ic.motherName = :#{#updateRequest.motherName}, ic.nationalityId = :#{#updateRequest.nationalityId} " +
            "where ic.id=:#{#id} and ic.customer.isDeleted=false ")
    void updateIndividualCustomerInfoById(Integer id, @Param("updateRequest") UpdateIndividualCustomerInfoRequest updateRequest);

    @Modifying
    @Query("update IndividualCustomer ic set ic.homePhone = :#{#updateRequest.homePhone}," +
            " ic.mobilePhone = :#{#updateRequest.mobilePhone}" +
            " where ic.id=:#{#id}")
    void updateCustomerContactMediumById(Integer id, UpdateContactMediumRequest updateRequest);
}