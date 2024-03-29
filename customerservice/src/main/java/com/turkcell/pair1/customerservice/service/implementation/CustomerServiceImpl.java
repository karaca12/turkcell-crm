package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import com.turkcell.pair1.customerservice.service.mapper.CustomerMapper;
import com.turkcell.pair1.customerservice.service.rules.CustomerBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final OrderServiceClient orderServiceClient;
    private final AddressService addressService;
    private final CustomerBusinessRules businessRules;


    @Override
    public List<SearchCustomerResponse> search(SearchCustomerRequest request) {
        List<SearchCustomerResponse> response = new ArrayList<>();
        if (request.getOrderNumber() != null) {
            String customerId = orderServiceClient.getCustomerIdByOrderId(request.getOrderNumber());
            Customer customer = customerRepository.findByCustomerId(customerId).orElseThrow(() ->
                    new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR)));

            response.add(CustomerMapper.INSTANCE.getSearchCustomerResponseFromCustomer(customer));

        } else {
            response = customerRepository.search(request);
            if (response.isEmpty()) {
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR));
            }
        }
        return response;
    }

    @Override
    public CreateCustomerResponse create(CreateCustomerRequest request) {
        businessRules.customerWithSameNationalityIdCannotExist(request.getNationalityId());
        Customer customer = CustomerMapper.INSTANCE.getCustomerFromCreateCustomerRequest(request);
        customer.setCustomerId(UUID.randomUUID().toString());
        Customer savedCustomer = customerRepository.save(customer);
        CreateCustomerResponse response=CustomerMapper.INSTANCE.getCreateCustomerResponseFromCustomer(savedCustomer);
        response.setAddressList(addressService.addAddressesForCustomer(request.getAddressList(), savedCustomer));
        return response;
    }

    @Override
    public void checkNationalityId(Integer nationalityId) {
        businessRules.customerWithSameNationalityIdCannotExist(nationalityId);
    }

    @Override
    public void updateInfo(UpdateCustomerInfoRequest request) {
        customerRepository.updateCustomerInfoById(request);
    }

    @Override
    public void createAddress(Integer id, List<AddAddressToCustomerRequest> request) {
        addressService.addAddressesForCustomer(request, customerRepository.findById(id).orElseThrow());
    }

    @Override
    public String lbTest() {
        return orderServiceClient.lbTest();
    }

}
