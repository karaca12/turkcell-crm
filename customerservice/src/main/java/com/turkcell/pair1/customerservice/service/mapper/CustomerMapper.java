package com.turkcell.pair1.customerservice.service.mapper;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.CreateIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerContactInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "addressList", target = "addresses")
    Customer getCustomerFromCreateRequest(CreateIndividualCustomerRequest request);

    @Mapping(source = "individualCustomer.homePhone", target = "homePhone")
    @Mapping(source = "individualCustomer.mobilePhone", target = "mobilePhone")
    GetIndividualCustomerContactInfoResponse getCustomerContactInfoResponseFromCustomer(Customer customer);
}