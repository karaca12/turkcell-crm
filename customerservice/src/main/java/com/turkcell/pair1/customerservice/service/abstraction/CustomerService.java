package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.service.dto.request.*;
import com.turkcell.pair1.customerservice.service.dto.response.*;

import java.util.List;

public interface CustomerService {
    List<SearchCustomerResponse> search(SearchCustomerRequest request, PageInfo pageInfo);

    CreateCustomerResponse create(CreateCustomerRequest request);

    void checkNationalityId(String nationalityId);

    void updateCustomerInfoByCustomerId(String customerId,UpdateCustomerInfoRequest request);

    GetCustomerInfoResponse getCustomerInfoByCustomerId(String customerId);

    List<GetAddressResponse> getCustomerAddressesByCustomerId(String customerId);

    GetCustomerContactInfoResponse getCustomerContactInfoByCustomerId(String customerId);

    void updateCustomerAddressByCustomerId(String customerId, UpdateAddressRequest request);

    void deleteCustomerByCustomerId(String customerId);

    void updateCustomerContactMediumByCustomerId(String customerId, UpdateContactMediumRequest request);

    GetAddressResponse createAddressToCustomerByCustomerId(String customerId, AddAddressToCustomerRequest request);

    void deleteAddressByCustomerIdAndAddressId(String customerId, Integer addressId);

    GetAddressResponse setPrimaryAddressByCustomerIdAndAddressId(String customerId, Integer addressId);
}

