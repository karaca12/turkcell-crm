package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;

import java.util.List;

public interface AddressService {

    List<GetAddressResponse> addAddressesForCustomer(List<AddAddressToCustomerRequest> request, Customer customer);

    List<GetAddressResponse> getAddressesFromCustomerByCustomerId(Customer customer);


    GetAddressResponse updateAddressForCustomer(UpdateAddressRequest updatedAddresses, Customer customer);

    GetAddressResponse addAddressForCustomer(AddAddressToCustomerRequest request, Customer customer);

    void deleteAddressById(Integer addressId, Customer customer);

    GetAddressResponse setPrimaryAddressById(Integer addressId, Customer customer);
}
