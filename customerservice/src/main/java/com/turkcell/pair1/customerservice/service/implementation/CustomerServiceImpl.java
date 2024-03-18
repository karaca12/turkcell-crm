package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.repository.CustomerRepository;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
