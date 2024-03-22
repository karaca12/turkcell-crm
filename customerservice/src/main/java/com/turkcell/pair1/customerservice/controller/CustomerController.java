package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("search")
    public List<SearchCustomerResponse> search(@RequestBody SearchCustomerRequest request){
        return customerService.search(request);
    }
    @GetMapping("/{customerId}")
    public GetCustomerInfoResponse getByCustomerId(@PathVariable Integer customerId){
        return customerService.getByCustomerId(customerId);
    }

    @PostMapping
    public Customer create(@RequestBody @Valid CreateCustomerRequest request){
        return customerService.create(request);
    }

    @PostMapping("/nationalityId")
    public void checkNationalityId(@RequestParam Integer nationalityId){
        customerService.checkNationalityId(nationalityId);
    }
}
