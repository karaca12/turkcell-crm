package com.turkcell.pair1.customerservice.service.mapper;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);


    Address addAddressToCustomerRequestToAddress(AddAddressToCustomerRequest request);

    @Mapping(source = "street.name",target = "street")
    @Mapping(source = "street.city.name",target = "city")
    GetAddressResponse getAddressResponseFromAddress(Address address);

    List<GetAddressResponse> getAddressResponsesFromAddresses(List<Address> addresses);

}