package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateCustomerInfoRequest;
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
                                               @RequestParam int page,@RequestParam int size){
        PageInfo pageInfo=new PageInfo(page,size);
        return customerService.search(request,pageInfo);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCustomerResponse create(@RequestBody @Valid CreateCustomerRequest request){
        return customerService.create(request);
    }

    @PostMapping("nationalityId")
    @ResponseStatus(HttpStatus.OK)
    public void checkNationalityId(@RequestParam String nationalityId){
        customerService.checkNationalityId(nationalityId);
    }

    @PutMapping("update/info")
    @ResponseStatus(HttpStatus.OK)
    public void updateInfo(@RequestBody @Valid UpdateCustomerInfoRequest request){
        customerService.updateInfo(request);
    }

    @PostMapping("create/address/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAddress(@PathVariable Integer id, @RequestBody List<AddAddressToCustomerRequest> request){
        customerService.createAddress(id,request);
    }

    @GetMapping("getCustomerInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerInfoResponse getCustomerInfoByCustomerId(@PathVariable String customerId){
        return customerService.getCustomerInfoByCustomerId(customerId);
    }

    @GetMapping("getCustomerAddressesByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAddressResponse> getCustomerAddressesById(@PathVariable String customerId){
        return customerService.getCustomerAddressesByCustomerId(customerId);
    }
    @GetMapping("getCustomerContactInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetCustomerContactInfoResponse getCustomerContactInfoByCustomerId(@PathVariable String customerId){
        return customerService.getCustomerContactInfoByCustomerId(customerId);
    }

}
