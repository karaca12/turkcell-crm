package com.turkcell.pair1.customerservice.controller;

import com.turkcell.pair1.customerservice.service.abstraction.IndividualCustomerService;
import com.turkcell.pair1.customerservice.service.dto.request.CreateIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.SearchIndividualCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateContactMediumRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateIndividualCustomerInfoRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateIndividualCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerContactInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetIndividualCustomerInfoResponse;
import com.turkcell.pair1.customerservice.service.dto.response.SearchIndividualCustomerResponse;
import com.turkcell.pair1.paging.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers/individualCustomers")
@RequiredArgsConstructor
public class IndividualCustomerController {
    private final IndividualCustomerService individualCustomerService;

    @PostMapping("search")
    @ResponseStatus(HttpStatus.OK)
    public List<SearchIndividualCustomerResponse> search(@RequestBody @Valid SearchIndividualCustomerRequest request,
                                                         @RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return individualCustomerService.search(request, pageInfo);
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateIndividualCustomerResponse create(@RequestBody @Valid CreateIndividualCustomerRequest request) {
        return individualCustomerService.create(request);
    }

    @GetMapping("checkIfNationalityIdUnique/{nationalityId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean checkIfNationalityIdUnique(@PathVariable String nationalityId) {
        return individualCustomerService.checkNationalityId(nationalityId);
    }

    @PutMapping("updateIndividualCustomerInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateIndividualCustomerInfoByCustomerId(@PathVariable String customerId, @RequestBody @Valid UpdateIndividualCustomerInfoRequest request) {
        individualCustomerService.updateIndividualCustomerInfoByCustomerId(customerId, request);
    }

    @GetMapping("getIndividualCustomerInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerInfoResponse getIndividualCustomerInfoByCustomerId(@PathVariable String customerId) {
        return individualCustomerService.getIndividualCustomerInfoByCustomerId(customerId);
    }

    @GetMapping("getIndividualCustomerContactInfoByCustomerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public GetIndividualCustomerContactInfoResponse getIndividualCustomerContactInfoByCustomerId(@PathVariable String customerId) {
        return individualCustomerService.getIndividualCustomerContactInfoByCustomerId(customerId);
    }

    @PutMapping("updateIndividualCustomerContactMediumByCustomerId/{customerId}")
    public void updateIndividualCustomerContactMediumByCustomerId(@PathVariable String customerId, @Valid @RequestBody UpdateContactMediumRequest request) {
        individualCustomerService.updateIndividualCustomerContactMediumByCustomerId(customerId, request);
    }

    @DeleteMapping("deleteIndividualCustomerByCustomerId/{customerId}")
    public void deleteIndividualCustomerByCustomerId(@PathVariable String customerId) {
        individualCustomerService.deleteIndividualCustomerByCustomerId(customerId);
    }
}