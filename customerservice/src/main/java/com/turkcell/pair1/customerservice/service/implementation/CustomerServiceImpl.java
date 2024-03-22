package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.client.OrderServiceClient;
import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.exception.types.DuplicateEntityException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import com.turkcell.pair1.customerservice.service.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final WebClient.Builder webClient;
    private final OrderServiceClient orderServiceClient;

    public CustomerServiceImpl(CustomerRepository customerRepository, MessageService messageService, WebClient.Builder webClient, OrderServiceClient orderServiceClient) {
        this.customerRepository = customerRepository;
        this.messageService = messageService;
        this.webClient = webClient;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public List<SearchCustomerResponse> search(SearchCustomerRequest request) {

        /*
        //webflux
        var result = webClient.build()
                .get()
                .uri("http://localhost:8081/api/orders?orderId=" + request.getOrderNumber())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        System.out.println("Deneme: " + result);
        */

        //openfeign


        if (!request.getOrderNumber().isEmpty()) {
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


        Customer customer = CustomerMapper.INSTANCE.getCustomerFromCreateCustomerRequest(request);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void checkNationalityId(Integer nationalityId) {
        if (customerRepository.existsByNationalityId(nationalityId)) {
            throw new DuplicateEntityException("nationalityId", messageService.getMessage(Messages.BusinessErrors.DUPLICATE_NATIONALITY_ID_ERROR));
        }
    }
}
