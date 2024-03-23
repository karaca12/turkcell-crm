package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.entity.Street;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;

import java.util.List;

public interface AddressService {
    void saveList(List<Address> addresses);

    List<Address> createAddressesForCustomer(CreateCustomerRequest request, Customer customer);
}
