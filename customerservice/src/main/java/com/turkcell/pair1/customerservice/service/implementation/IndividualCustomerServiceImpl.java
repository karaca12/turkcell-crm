package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.entity.IndividualCustomer;
import com.turkcell.pair1.customerservice.repository.IndividualCustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.abstraction.IndividualCustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.CreateIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateIndividualCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateIndividualCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerContactInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchIndividualCustomerResponse;
import com.turkcell.pair1.customerservice.service.mapper.CustomerMapper;
import com.turkcell.pair1.customerservice.service.mapper.IndividualCustomerMapper;
import com.turkcell.pair1.customerservice.service.rules.IndividualCustomerBusinessRules;
import com.turkcell.pair1.paging.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndividualCustomerServiceImpl implements IndividualCustomerService {
    private final IndividualCustomerRepository individualCustomerRepository;
    private final AddressService addressService;
    private final IndividualCustomerBusinessRules businessRules;
    private final CustomerService customerService;


    @Override
    public List<SearchIndividualCustomerResponse> search(SearchIndividualCustomerRequest request, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());

        List<SearchIndividualCustomerResponse> response = individualCustomerRepository.search(request, businessRules.getCustomerIdFromOrderNumberOrAccountNumber(request.getOrderNumber(), request.getAccountNumber()), pageable);
        businessRules.checkIfSearchIsEmpty(response);
        return businessRules.sortSearchResponse(response);
    }

    @Override
    @Transactional
    public CreateIndividualCustomerResponse create(CreateIndividualCustomerRequest request) {
        businessRules.individualCustomerWithSameNationalityIdCannotExistUnlessForeign(request.getNationalityId());
        Customer customer = CustomerMapper.INSTANCE.getCustomerFromCreateRequest(request);
        customer.setCustomerId(customerService.generateCustomerId());
        Customer savedCustomer = customerService.save(customer);
        IndividualCustomer individualCustomer = IndividualCustomerMapper.INSTANCE.getIndividualCustomerFromCreateRequest(request);
        individualCustomer.setCustomer(savedCustomer);
        IndividualCustomer savedIndividualCustomer = individualCustomerRepository.save(individualCustomer);
        CreateIndividualCustomerResponse response = IndividualCustomerMapper.INSTANCE.getCreateIndividualCustomerResponseFromIndividualCustomer(savedIndividualCustomer);
        response.setAddressList(addressService.addAddressesForCustomer(request.getAddressList(), savedCustomer));
        return response;
    }

    @Override
    public boolean checkNationalityId(String nationalityId) {
        return businessRules.individualCustomerWithSameNationalityIdCannotExistUnlessForeign(nationalityId);
    }

    @Override
    @Transactional
    public void updateIndividualCustomerInfoByCustomerId(String customerId, UpdateIndividualCustomerInfoRequest request) {
        Customer customer = customerService.getCustomerFromOptional(customerService.findByIsDeletedFalseAndCustomerId(customerId));
        businessRules.checkIfNationalityIdAlreadyExists(customer, request);
        individualCustomerRepository.updateIndividualCustomerInfoById(customer.getIndividualCustomer().getId(), request);
        customer.setUpdatedAt(LocalDateTime.now());
        customerService.save(customer);
    }


    @Override
    public GetIndividualCustomerInfoResponse getIndividualCustomerInfoByCustomerId(String customerId) {
        IndividualCustomer individualCustomer = customerService.getCustomerFromOptional(customerService.findByIsDeletedFalseAndCustomerId(customerId)).getIndividualCustomer();
        return IndividualCustomerMapper.INSTANCE.getIndividualCustomerInfoResponseFromCustomer(individualCustomer);
    }

    @Override
    public GetIndividualCustomerContactInfoResponse getIndividualCustomerContactInfoByCustomerId(String customerId) {
        Customer customer = customerService.getCustomerFromOptional(customerService.findByIsDeletedFalseAndCustomerId(customerId));
        return CustomerMapper.INSTANCE.getCustomerContactInfoResponseFromCustomer(customer);
    }

    @Override
    @Transactional
    public void deleteIndividualCustomerByCustomerId(String customerId) {
        Customer customer = customerService.getCustomerFromOptional(customerService.findByIsDeletedFalseAndCustomerId(customerId));
        businessRules.ensureCustomerHasNoActiveProducts(customer);
        customer.setDeleted(true);
        customer.setDeletedAt(LocalDateTime.now());
        addressService.deleteAddressesWhenDeletingCustomer(customer);
        individualCustomerRepository.save(customer.getIndividualCustomer());
    }

    @Override
    @Transactional
    public void updateIndividualCustomerContactMediumByCustomerId(String customerId, UpdateContactMediumRequest request) {
        Customer customer = customerService.getCustomerFromOptional(customerService.findByIsDeletedFalseAndCustomerId(customerId));
        individualCustomerRepository.updateCustomerContactMediumById(customer.getId(), request);
        customerService.updateIndividualCustomerContactMediumByCustomerId(customerId, request);
    }
}