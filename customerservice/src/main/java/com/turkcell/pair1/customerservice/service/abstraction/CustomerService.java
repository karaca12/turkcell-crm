package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;

import java.util.List;

public interface CustomerService {
    List<SearchCustomerResponse> search(SearchCustomerRequest request);

    GetCustomerInfoResponse getByCustomerId(Integer customerId);

    Customer create(CreateCustomerRequest request);

    void checkNationalityId(Integer nationalityId);
}
