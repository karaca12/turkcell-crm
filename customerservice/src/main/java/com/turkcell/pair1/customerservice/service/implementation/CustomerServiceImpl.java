package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateAddressToCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.customerservice.service.rules.CustomerBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules businessRules;
    private final AddressService addressService;

    @Override
    public Optional<Customer> findByIsDeletedFalseAndCustomerId(String customerId) {
        return customerRepository.findByIsDeletedFalseAndCustomerId(customerId);
    }

    @Override
    public String generateCustomerId() {
        return businessRules.generateCustomerId();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean checkByCustomerIdIfCustomerExists(String customerId) {
        return customerRepository.existsByCustomerId(customerId);
    }

    @Override
    public List<GetAddressResponse> getCustomerAddressesByCustomerId(String customerId, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        return addressService.getAddressesFromCustomerByCustomerId(businessRules.getCustomerFromOptional(findByIsDeletedFalseAndCustomerId(customerId)), pageable);
    }

    @Override
    public Customer getCustomerFromOptional(Optional<Customer> optionalCustomer) {
        return businessRules.getCustomerFromOptional(optionalCustomer);
    }

    @Override
    @Transactional
    public void updateCustomerAddressByCustomerId(String customerId, UpdateAddressRequest request) {
        Customer customer = businessRules.getCustomerFromOptional(findByIsDeletedFalseAndCustomerId(customerId));
        addressService.updateAddressForCustomer(request, customer);
    }

    @Override
    @Transactional
    public CreateAddressToCustomerResponse createAddressToCustomerByCustomerId(String customerId, AddAddressToCustomerRequest request) {
        Customer customer = businessRules.getCustomerFromOptional(findByIsDeletedFalseAndCustomerId(customerId));
        return addressService.addAddressForCustomer(request, customer);
    }

    @Override
    @Transactional
    public void deleteAddressByCustomerIdAndAddressId(String customerId, Integer addressId) {
        Customer customer = businessRules.getCustomerFromOptional(findByIsDeletedFalseAndCustomerId(customerId));
        addressService.deleteAddressById(addressId, customer);
    }

    @Override
    @Transactional
    public void setPrimaryAddressByCustomerIdAndAddressId(String customerId, Integer addressId) {
        Customer customer = businessRules.getCustomerFromOptional(findByIsDeletedFalseAndCustomerId(customerId));
        addressService.setPrimaryAddressById(addressId, customer);
    }

    @Override
    public void updateIndividualCustomerContactMediumByCustomerId(String customerId, UpdateContactMediumRequest request) {
        Customer customer = getCustomerFromOptional(findByIsDeletedFalseAndCustomerId(customerId));
        customerRepository.updateCustomerContactMediumById(customer.getId(), request);
    }
}