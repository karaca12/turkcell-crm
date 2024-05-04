package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateAddressToCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.paging.PageInfo;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByIsDeletedFalseAndCustomerId(String customerId);

    String generateCustomerId();

    Customer save(Customer customer);

    boolean checkByCustomerIdIfCustomerExists(String customerId);

    List<GetAddressResponse> getCustomerAddressesByCustomerId(String customerId, PageInfo pageInfo);

    Customer getCustomerFromOptional(Optional<Customer> optionalCustomer);

    void updateCustomerAddressByCustomerId(String customerId, UpdateAddressRequest request);

    CreateAddressToCustomerResponse createAddressToCustomerByCustomerId(String customerId, AddAddressToCustomerRequest request);

    void deleteAddressByCustomerIdAndAddressId(String customerId, Integer addressId);

    void setPrimaryAddressByCustomerIdAndAddressId(String customerId, Integer addressId);

    void updateIndividualCustomerContactMediumByCustomerId(String customerId, UpdateContactMediumRequest request);
}