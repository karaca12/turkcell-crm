package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.exception.types.DuplicateEntityException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import com.turkcell.pair1.customerservice.service.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final OrderServiceClient orderServiceClient;
    private final AddressService addressService;

    public CustomerServiceImpl(CustomerRepository customerRepository, MessageService messageService, OrderServiceClient orderServiceClient, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.messageService = messageService;
        this.orderServiceClient = orderServiceClient;
        this.addressService = addressService;
    }

    @Override
    public List<SearchCustomerResponse> search(SearchCustomerRequest request) {

        if (request.getOrderNumber()!=null) {
            int customerId = orderServiceClient.getCustomerIdByOrderId(request.getOrderNumber());
            List<SearchCustomerResponse> response = new ArrayList<>();
            Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                    new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR)));
            SearchCustomerResponse searchCustomerResponse = CustomerMapper.INSTANCE.getSearchCustomerResponseFromCustomer(customer);
            response.add(searchCustomerResponse);
            return response;
        } else {
            List<SearchCustomerResponse> response = customerRepository.search(request);
            if (response.isEmpty()) {
                throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR));
            }
            return response;
        }
    }

    @Override
    public GetCustomerInfoResponse getByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findByCustomerId(String.valueOf(customerId))
                .orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR)));
        return CustomerMapper.INSTANCE.getCustomerInfoResponseFromCustomer(customer);
    }

    @Override
    public Customer create(CreateCustomerRequest request) {
        checkNationalityId(request.getNationalityId());
        Customer customer = CustomerMapper.INSTANCE.getCustomerFromCreateCustomerRequest(request);
        customerRepository.save(customer);
        addressService.createAddressesForCustomer(request, customer);
        return customer;
    }

    @Override
    public void checkNationalityId(Integer nationalityId) {
        if (customerRepository.existsByNationalityId(nationalityId)) {
            throw new DuplicateEntityException("nationalityId", messageService.getMessage(Messages.BusinessErrors.DUPLICATE_NATIONALITY_ID_ERROR));
        }
    }
}
