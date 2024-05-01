package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.service.dto.request.CreateIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateIndividualCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateIndividualCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerContactInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchIndividualCustomerResponse;

import java.util.List;

public interface IndividualCustomerService {
    List<SearchIndividualCustomerResponse> search(SearchIndividualCustomerRequest request, PageInfo pageInfo);

    CreateIndividualCustomerResponse create(CreateIndividualCustomerRequest request);

    boolean checkNationalityId(String nationalityId);

    void updateIndividualCustomerInfoByCustomerId(String customerId, UpdateIndividualCustomerInfoRequest request);

    GetIndividualCustomerInfoResponse getIndividualCustomerInfoByCustomerId(String customerId);

    GetIndividualCustomerContactInfoResponse getIndividualCustomerContactInfoByCustomerId(String customerId);

    void deleteIndividualCustomerByCustomerId(String customerId);

    void updateIndividualCustomerContactMediumByCustomerId(String customerId, UpdateContactMediumRequest request);
}