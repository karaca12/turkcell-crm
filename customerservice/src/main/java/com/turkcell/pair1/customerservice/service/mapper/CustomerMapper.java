package com.turkcell.pair1.customerservice.service.mapper;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    GetCustomerInfoResponse getCustomerInfoResponseFromCustomer(Customer customer);
}
