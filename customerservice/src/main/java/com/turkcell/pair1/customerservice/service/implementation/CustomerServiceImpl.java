package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.core.service.abstraction.MessageService;
import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import com.turkcell.pair1.customerservice.service.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MessageService messageService;
    private final WebClient.Builder webClient;

    public CustomerServiceImpl(CustomerRepository customerRepository, MessageService messageService, WebClient.Builder webClient) {
        this.customerRepository = customerRepository;
        this.messageService = messageService;
        this.webClient = webClient;
    }

    @Override
    public List<SearchCustomerResponse> search(SearchCustomerRequest request) {
        var result = webClient.build()
                .get()
                .uri("http://localhost:8081/api/orders?orderId=" + request.getOrderNumber())
                .retrieve()
                .bodyToMono(Integer.class)
                .block();
        System.out.println("Deneme: " + result);
        List<SearchCustomerResponse> response = customerRepository.search(request);
        if (response.isEmpty()) {
            throw new BusinessException("No customer found!");
        }
        return response;
    }

    @Override
    public GetCustomerInfoResponse getByCustomerId(Integer customerId) {
        Customer customer = customerRepository.findByCustomerId(String.valueOf(customerId))
                .orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND_ERROR)));
        return CustomerMapper.INSTANCE.getCustomerInfoResponseFromCustomer(customer);
    }
}
