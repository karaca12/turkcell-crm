package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.*;
import com.turkcell.pair1.customerservice.service.dto.response.*;
import com.turkcell.pair1.customerservice.service.mapper.CustomerMapper;
import com.turkcell.pair1.customerservice.service.rules.CustomerBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    private final CustomerBusinessRules businessRules;


    @Override
    public List<SearchCustomerResponse> search(SearchCustomerRequest request, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());

        List<SearchCustomerResponse> response = customerRepository.search(request, businessRules.getCustomerIdFromOrderNumberOrAccountNumber(request.getOrderNumber(),request.getAccountNumber()), pageable);
        businessRules.checkIfSearchIsEmpty(response);
        return businessRules.sortSearchResponse(response);
    }

    @Override
    @Transactional
    public CreateCustomerResponse create(CreateCustomerRequest request) {
        businessRules.customerWithSameNationalityIdCannotExistUnlessForeign(request.getNationalityId());
        Customer customer = CustomerMapper.INSTANCE.getCustomerFromCreateRequest(request);
        customer.setCustomerId(businessRules.generateCustomerId());
        Customer savedCustomer = customerRepository.save(customer);
        CreateCustomerResponse response = CustomerMapper.INSTANCE.getCreateCustomerResponseFromCustomer(savedCustomer);
        response.setAddressList(addressService.addAddressesForCustomer(request.getAddressList(), savedCustomer));
        return response;
    }

    @Override
    public boolean checkNationalityId(String nationalityId) {
        return businessRules.customerWithSameNationalityIdCannotExistUnlessForeign(nationalityId);
    }

    @Override
    @Transactional
    public void updateCustomerInfoByCustomerId(String customerId, UpdateCustomerInfoRequest request) {
        Customer customer = businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId));
        businessRules.checkIfNationalityIdAlreadyExists(customer,request);
        customerRepository.updateCustomerInfoById(customer.getId(), request);
    }


    @Override
    public GetCustomerInfoResponse getCustomerInfoByCustomerId(String customerId) {
        return CustomerMapper.INSTANCE.getCustomerInfoResponseFromCustomer(
                businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId)));
    }

    @Override
    public List<GetAddressResponse> getCustomerAddressesByCustomerId(String customerId,PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        return addressService.getAddressesFromCustomerByCustomerId(businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId)),pageable);
    }

    @Override
    public GetCustomerContactInfoResponse getCustomerContactInfoByCustomerId(String customerId) {
        return CustomerMapper.INSTANCE.getCustomerContactInfoResponseFromCustomer(
                businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId)));
    }

    @Override
    @Transactional
    public void updateCustomerAddressByCustomerId(String customerId, UpdateAddressRequest request) {
        Customer customer = businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId));
        addressService.updateAddressForCustomer(request, customer);
    }

    @Override
    @Transactional
    public CreateAddressToCustomerResponse createAddressToCustomerByCustomerId(String customerId, AddAddressToCustomerRequest request) {
        Customer customer = businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId));
        return addressService.addAddressForCustomer(request, customer);
    }

    @Override
    @Transactional
    public void deleteAddressByCustomerIdAndAddressId(String customerId, Integer addressId) {
        Customer customer = businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId));
        addressService.deleteAddressById(addressId, customer);
    }

    @Override
    @Transactional
    public GetAddressResponse setPrimaryAddressByCustomerIdAndAddressId(String customerId, Integer addressId) {
        Customer customer = businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId));
        return addressService.setPrimaryAddressById(addressId, customer);
    }


    @Override
    @Transactional
    public void deleteCustomerByCustomerId(String customerId) {
        Customer customer = businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId));
        businessRules.ensureCustomerHasNoActiveProducts(customer);
        customer.setDeleted(true);
        customer.setDeletedAt(LocalDateTime.now());
        addressService.deleteAddressesWhenDeletingCustomer(customer);
        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void updateCustomerContactMediumByCustomerId(String customerId, UpdateContactMediumRequest request) {
        Customer customer = businessRules.getCustomerFromOptional(customerRepository.findByIsDeletedFalseAndCustomerId(customerId));
        customerRepository.updateCustomerContactMediumById(customer.getId(), request);
    }

}
