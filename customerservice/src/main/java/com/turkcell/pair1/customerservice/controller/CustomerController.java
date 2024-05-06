package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateAddressToCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.paging.PageInfo;
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

    @GetMapping("checkByCustomerIdIfCustomerExists/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkByCustomerIdIfCustomerExists(@PathVariable String customerId) {
        return customerService.checkByCustomerIdIfCustomerExists(customerId);
    }

    @GetMapping("getCustomerAddressesByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAddressResponse> getCustomerAddressesById(@PathVariable String customerId,
                                                             @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return customerService.getCustomerAddressesByCustomerId(customerId, pageInfo);
    }

    @PostMapping("createAddressToCustomerByCustomerId/{customerId}")
    public CreateAddressToCustomerResponse createAddressToCustomerByCustomerId(@PathVariable String customerId, @Valid @RequestBody AddAddressToCustomerRequest request) {
        return customerService.createAddressToCustomerByCustomerId(customerId, request);
    }

    @PutMapping("updateCustomerAddressByCustomerId/{customerId}")
    public void updateCustomerAddressByCustomerId(@PathVariable String customerId, @Valid @RequestBody UpdateAddressRequest request) {
        customerService.updateCustomerAddressByCustomerId(customerId, request);
    }

    @DeleteMapping("deleteAddressByCustomerIdAndAddressId/{customerId}/{addressId}")
    public void deleteAddressByCustomerIdAndAddressId(@PathVariable String customerId, @PathVariable Integer addressId) {
        customerService.deleteAddressByCustomerIdAndAddressId(customerId, addressId);
    }

    @PutMapping("setPrimaryAddressByCustomerIdAndAddressId/{customerId}/{addressId}")
    public void setPrimaryAddressByCustomerIdAndAddressId(@PathVariable String customerId, @PathVariable Integer addressId) {
        customerService.setPrimaryAddressByCustomerIdAndAddressId(customerId, addressId);
    }
}