package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<SearchCustomerResponse> search(SearchCustomerRequest request) {
        List<SearchCustomerResponse> response = customerRepository.search(request);
        if (response.isEmpty()) {
            throw new BusinessException("No customer found!");
        }
        return response;
    }

    @Override
    public GetCustomerInfoResponse getByCustomerId(Integer customerId) {
        String customerIdString = String.valueOf(customerId);
        Customer customer = customerRepository.findByCustomerId(customerIdString).orElseThrow(() -> new BusinessException("No customer found!"));
        return new GetCustomerInfoResponse(
                customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getBirthDate(),
                customer.getGender(), customer.getFatherName(), customer.getMotherName(), customer.getNationalityId());
    }
}
