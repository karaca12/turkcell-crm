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
    public List<SearchCustomerResponse> search(@RequestBody SearchCustomerRequest request,
                                               @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return customerService.search(request, pageInfo);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse create(@RequestBody @Valid CreateCustomerRequest request) {
        return customerService.create(request);
    }

    @PostMapping("nationalityId")
    @ResponseStatus(HttpStatus.OK)
    public void checkNationalityId(@RequestParam String nationalityId) {
        customerService.checkNationalityId(nationalityId);
    }

    @PutMapping("update/info")
    @ResponseStatus(HttpStatus.OK)
    public void updateInfo(@RequestBody @Valid UpdateCustomerInfoRequest request) {
        customerService.updateInfo(request);
    }

    @GetMapping("getCustomerInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerInfoResponse getCustomerInfoByCustomerId(@PathVariable String customerId) {
        return customerService.getCustomerInfoByCustomerId(customerId);
    }

    @GetMapping("getCustomerAddressesByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAddressResponse> getCustomerAddressesById(@PathVariable String customerId) {
        return customerService.getCustomerAddressesByCustomerId(customerId);
    }

    @GetMapping("getCustomerContactInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerContactInfoResponse getCustomerContactInfoByCustomerId(@PathVariable String customerId) {
        return customerService.getCustomerContactInfoByCustomerId(customerId);
    }

    @PostMapping("updateCustomerAddressesByCustomerId/{customerId}")
    public void updateCustomerAddressesByCustomerId(@PathVariable String customerId, @RequestBody AddUpdateAndDeleteAddressRequest request) {
        customerService.updateCustomerAddressesByCustomerId(customerId, request);
    }

    @DeleteMapping("deleteCustomerByCustomerId/{customerId}")
    public void deleteCustomerByCustomerId(@PathVariable String customerId) {
        customerService.deleteCustomerByCustomerId(customerId);
    }

    @PostMapping("updateCustomerContactMediumByCustomerId/{customerId}")
    public void updateCustomerContactMediumByCustomerId(@PathVariable String customerId, @RequestBody UpdateContactMediumRequest request) {
        customerService.updateCustomerContactMediumByCustomerId(customerId, request);
    }
}
