package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.core.business.paging.PageInfo;
import com.turkcell.pair1.invoiceservice.service.abstraction.BillingAccountService;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountInfoRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateAddressToBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetBillingAccountInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice/billingAccounts")
@RequiredArgsConstructor
public class BillingAccountController {
    private final BillingAccountService billingAccountService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBillingAccountResponse create(@RequestBody @Valid CreateBillingAccountRequest request) {
        return billingAccountService.create(request);
    }

    @PostMapping("createAddressToBillingAccountByAccountNumber/{accountNumber}")
    public CreateAddressToBillingAccountResponse createAddressToBillingAccountByAccountNumber(@PathVariable String accountNumber, @Valid @RequestBody AddAddressToAccountRequest request) {
        return billingAccountService.createAddressToBillingAccountByAccountNumber(accountNumber, request);
    }

    @PutMapping("updateBillingAccountByAccountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBillingAccountInfoByAccountNumber(@PathVariable String accountNumber, @RequestBody @Valid UpdateBillingAccountInfoRequest request) {
        billingAccountService.updateBillingAccountInfoByAccountNumber(accountNumber, request);
    }

    @PutMapping("updateBillingAccountAddressByAccountNumber/{accountNumber}")
    public void updateBillingAccountAddressByAccountNumber(@PathVariable String accountNumber, @Valid @RequestBody UpdateAddressRequest request) {
        billingAccountService.updateBillingAccountAddressByAccountNumber(accountNumber, request);
    }

    @PutMapping("setPrimaryAddressByAccountNumberAndAddressId/{accountNumber}/{addressId}")
    public void setPrimaryAddressByAccountNumberAndAddressId(@PathVariable String accountNumber, @PathVariable Integer addressId) {
        billingAccountService.setPrimaryAddressByAccountNumberAndAddressId(accountNumber, addressId);
    }



    @GetMapping("getBillingAccountInfoByAccountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public GetBillingAccountInfoResponse getBillingAccountInfoByAccountNumber(@PathVariable String accountNumber) {
        return billingAccountService.getBillingAccountInfoByAccountNumber(accountNumber);
    }

    @GetMapping("getBillingAccountAddressesByAccountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAddressResponse> getBillingAccountAddressesByAccountNumber(@PathVariable String accountNumber,@RequestParam int page, @RequestParam int size) {
        PageInfo pageInfo = new PageInfo(page, size);
        return billingAccountService.getBillingAccountAddressesByAccountNumber(accountNumber, pageInfo);
    }


    @DeleteMapping("deleteBillingAccountByAccountNumber/{accountNumber}")
    public void deleteBillingAccountByAccountNumber(@PathVariable String accountNumber) {
        billingAccountService.deleteBillingAccountByAccountNumber(accountNumber);
    }

    @DeleteMapping("deleteAddressByAccountNumberAndAddressId/{accountNumber}/{addressId}")
    public void deleteAddressByAccountNumberAndAddressId(@PathVariable String accountNumber, @PathVariable Integer addressId) {
        billingAccountService.deleteAddressByAccountNumberAndAddressId(accountNumber, addressId);
    }




}