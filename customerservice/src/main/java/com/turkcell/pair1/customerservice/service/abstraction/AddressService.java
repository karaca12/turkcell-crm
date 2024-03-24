package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;

import java.util.List;

public interface AddressService {

    void addAddressesForCustomer(List<AddAddressToCustomerRequest> request, Customer customer);
}
