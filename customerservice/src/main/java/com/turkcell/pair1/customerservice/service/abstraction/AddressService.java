package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;

import java.util.List;

public interface AddressService {

    List<GetAddressResponse> addAddressesForCustomer(List<AddAddressToCustomerRequest> request, Customer customer);
}
