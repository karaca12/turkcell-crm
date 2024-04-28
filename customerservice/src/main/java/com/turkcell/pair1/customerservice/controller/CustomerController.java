package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.*;
import com.turkcell.pair1.customerservice.service.dto.response.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchCustomerResponse> search(@RequestBody @Valid SearchCustomerRequest request,
                                               @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return customerService.search(request, pageInfo);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse create(@RequestBody @Valid CreateCustomerRequest request) {
        return customerService.create(request);
    }

    @GetMapping("checkIfNationalityIdUnique")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkIfNationalityIdUnique(@RequestParam String nationalityId) {
        return customerService.checkNationalityId(nationalityId);
    }

    @GetMapping("checkByCustomerIdIfCustomerExists/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkByCustomerIdIfCustomerExists(@PathVariable String customerId) {
        return customerService.checkByCustomerIdIfCustomerExists(customerId);
    }

    @PutMapping("updateCustomerInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomerInfoByCustomerId(@PathVariable String customerId,@RequestBody @Valid UpdateCustomerInfoRequest request) {
        customerService.updateCustomerInfoByCustomerId(customerId,request);
    }

    @GetMapping("getCustomerInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerInfoResponse getCustomerInfoByCustomerId(@PathVariable String customerId) {
        return customerService.getCustomerInfoByCustomerId(customerId);
    }

    @GetMapping("getCustomerAddressesByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAddressResponse> getCustomerAddressesById(@PathVariable String customerId,
                                                             @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return customerService.getCustomerAddressesByCustomerId(customerId,pageInfo);
    }

    @GetMapping("getCustomerContactInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerContactInfoResponse getCustomerContactInfoByCustomerId(@PathVariable String customerId) {
        return customerService.getCustomerContactInfoByCustomerId(customerId);
    }

    @PutMapping("updateCustomerAddressByCustomerId/{customerId}")
    public void updateCustomerAddressByCustomerId(@PathVariable String customerId, @Valid @RequestBody UpdateAddressRequest request) {
        customerService.updateCustomerAddressByCustomerId(customerId, request);
    }

    @PostMapping("createAddressToCustomerByCustomerId/{customerId}")
    public CreateAddressToCustomerResponse createAddressToCustomerByCustomerId(@PathVariable String customerId, @Valid @RequestBody AddAddressToCustomerRequest request) {
        return customerService.createAddressToCustomerByCustomerId(customerId, request);
    }
    @DeleteMapping("deleteAddressByCustomerIdAndAddressId/{customerId}/{addressId}")
    public void deleteAddressByCustomerIdAndAddressId(@PathVariable String customerId, @PathVariable Integer addressId) {
        customerService.deleteAddressByCustomerIdAndAddressId(customerId,addressId);
    }

    @PutMapping("setPrimaryAddressByCustomerIdAndAddressId/{customerId}/{addressId}")
    public void setPrimaryAddressByCustomerIdAndAddressId(@PathVariable String customerId, @PathVariable Integer addressId){
        customerService.setPrimaryAddressByCustomerIdAndAddressId(customerId,addressId);
    }

    @DeleteMapping("deleteCustomerByCustomerId/{customerId}")
    public void deleteCustomerByCustomerId(@PathVariable String customerId) {
        customerService.deleteCustomerByCustomerId(customerId);
    }

    @PutMapping("updateCustomerContactMediumByCustomerId/{customerId}")
    public void updateCustomerContactMediumByCustomerId(@PathVariable String customerId, @Valid @RequestBody UpdateContactMediumRequest request) {
        customerService.updateCustomerContactMediumByCustomerId(customerId, request);
    }
}
