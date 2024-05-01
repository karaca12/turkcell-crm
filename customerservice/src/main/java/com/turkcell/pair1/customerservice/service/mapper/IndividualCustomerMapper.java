package com.turkcell.pair1.customerservice.service.mapper;

import com.turkcell.pair1.customerservice.entity.IndividualCustomer;
import com.turkcell.pair1.customerservice.service.dto.request.CreateIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateIndividualCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IndividualCustomerMapper {
    IndividualCustomerMapper INSTANCE = Mappers.getMapper(IndividualCustomerMapper.class);

    IndividualCustomer getIndividualCustomerFromCreateRequest(CreateIndividualCustomerRequest request);

    @Mapping(source = "customer.email", target = "email")
    @Mapping(source = "customer.fax", target = "fax")
    @Mapping(source = "customer.customerId", target = "customerId")
    CreateIndividualCustomerResponse getCreateIndividualCustomerResponseFromIndividualCustomer(IndividualCustomer savedIndividualCustomer);

    GetIndividualCustomerInfoResponse getIndividualCustomerInfoResponseFromCustomer(IndividualCustomer individualCustomer);
}