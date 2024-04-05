package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.*;

import java.util.List;

public interface CustomerService {
    List<SearchCustomerResponse> search(SearchCustomerRequest request, PageInfo pageInfo);

    CreateCustomerResponse create(CreateCustomerRequest request);

    void checkNationalityId(String nationalityId);

    void updateInfo(UpdateCustomerInfoRequest request);

    void createAddress(Integer id, List<AddAddressToCustomerRequest> request);

    GetCustomerInfoResponse getCustomerInfoByCustomerId(String customerId);

    List<GetAddressResponse> getCustomerAddressesByCustomerId(String customerId);

    GetCustomerContactInfoResponse getCustomerContactInfoByCustomerId(String customerId);
}
