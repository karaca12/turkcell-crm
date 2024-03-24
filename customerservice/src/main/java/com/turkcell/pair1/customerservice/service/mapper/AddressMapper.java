package com.turkcell.pair1.customerservice.service.mapper;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.entity.Street;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);


    Address addAddressToCustomerRequesttoAddress(AddAddressToCustomerRequest dto);

}