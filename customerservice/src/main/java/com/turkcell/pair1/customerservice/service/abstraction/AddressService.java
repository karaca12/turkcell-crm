package com.turkcell.pair1.customerservice.service.abstraction;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateAddressToCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {

    List<GetAddressResponse> addAddressesForCustomer(List<AddAddressToCustomerRequest> request, Customer customer);

    List<GetAddressResponse> getAddressesFromCustomerByCustomerId(Customer customer, Pageable pageable);


    void updateAddressForCustomer(UpdateAddressRequest updatedAddresses, Customer customer);

    CreateAddressToCustomerResponse addAddressForCustomer(AddAddressToCustomerRequest request, Customer customer);

    void deleteAddressById(Integer addressId, Customer customer);

    void setPrimaryAddressById(Integer addressId, Customer customer);

    void deleteAddressesWhenDeletingCustomer(Customer customer);
}
