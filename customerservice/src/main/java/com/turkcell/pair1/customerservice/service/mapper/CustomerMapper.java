package com.turkcell.pair1.customerservice.service.mapper;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);


    SearchCustomerResponse getSearchCustomerResponseFromCustomer(Customer customer);

    Customer getCustomerFromCreateCustomerRequest(CreateCustomerRequest request);

    CreateCustomerResponse getCreateCustomerResponseFromCustomer(Customer savedCustomer);
}
