package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("search")
    public List<SearchCustomerResponse> search(@RequestBody SearchCustomerRequest request){
        return customerService.search(request);
    }
    @GetMapping("{customerId}")
    public GetCustomerInfoResponse getByCustomerId(@PathVariable Integer customerId){
        return customerService.getByCustomerId(customerId);
    }

    @PostMapping("create")
    public Customer create(@RequestBody @Valid CreateCustomerRequest request){
        return customerService.create(request);
    }

    @PostMapping("nationalityId")
    public void checkNationalityId(@RequestParam Integer nationalityId){
        customerService.checkNationalityId(nationalityId);
    }

    @PutMapping("update/info")
    public void updateInfo(@RequestBody @Valid UpdateCustomerInfoRequest request){
        customerService.updateInfo(request);
    }

    @PostMapping("create/address/{id}")
    public void createAddress(@PathVariable Integer id, @RequestBody List<AddAddressToCustomerRequest> request){
        customerService.createAddress(id,request);
    }



}
