package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.core.business.paging.PageInfo;
import com.turkcell.pair1.customerservice.service.abstraction.CustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchCustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("search")
    public List<SearchCustomerResponse> search(@RequestBody SearchCustomerRequest request,
                                               @RequestParam int page,@RequestParam int size){
        PageInfo pageInfo=new PageInfo(page,size);
        return customerService.search(request,pageInfo);
    }

    @PostMapping("create")
    public CreateCustomerResponse create(@RequestBody @Valid CreateCustomerRequest request){
        return customerService.create(request);
    }

    @PostMapping("nationalityId")
    public void checkNationalityId(@RequestParam String nationalityId){
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
