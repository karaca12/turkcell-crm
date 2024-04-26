package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.service.abstraction.BillingAccountService;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountInfoRequest;
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

    @PutMapping("updateBillingAccountByAccountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBillingAccountByAccountNumber(@PathVariable String accountNumber, @RequestBody @Valid UpdateBillingAccountInfoRequest request) {
        billingAccountService.updateBillingAccountInfoByAccountNumber(accountNumber, request);
    }

    @DeleteMapping("deleteBillingAccountByAccountNumber/{accountNumber}")
    public void deleteBillingAccountByAccountNumber(@PathVariable String accountNumber) {
        billingAccountService.deleteBillingAccountByAccountNumber(accountNumber);
    }

    @GetMapping("getBillingAccountAddressesByAccountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAddressResponse> getBillingAccountAddressesByAccountNumber(@PathVariable String accountNumber) {
        return billingAccountService.getBillingAccountAddressesByAccountNumber(accountNumber);
    }

    @PostMapping("createAddressToBillingAccountByAccountNumber/{accountNumber}")
    public GetAddressResponse createAddressToBillingAccountByAccountNumber(@PathVariable String accountNumber, @Valid @RequestBody AddAddressToAccountRequest request) {
        return billingAccountService.createAddressToBillingAccountByAccountNumber(accountNumber, request);
    }

    @DeleteMapping("deleteAddressByAccountNumberAndAddressId/{accountNumber}/{addressId}")
    public void deleteAddressByAccountNumberAndAddressId(@PathVariable String accountNumber, @PathVariable Integer addressId) {
        billingAccountService.deleteAddressByAccountNumberAndAddressId(accountNumber, addressId);
    }

    @PutMapping("setPrimaryAddressByAccountNumberAndAddressId/{accountNumber}/{addressId}")
    public GetAddressResponse setPrimaryAddressByAccountNumberAndAddressId(@PathVariable String accountNumber, @PathVariable Integer addressId) {
        return billingAccountService.setPrimaryAddressByAccountNumberAndAddressId(accountNumber, addressId);
    }

    @PutMapping("updateBillingAccountAddressByAccountNumber/{accountNumber}")
    public void updateBillingAccountAddressByAccountNumber(@PathVariable String accountNumber, @Valid @RequestBody UpdateAddressRequest request) {
        billingAccountService.updateBillingAccountAddressByAccountNumber(accountNumber, request);
    }

    @GetMapping("getBillingAccountInfoByAccountNumber/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public GetBillingAccountInfoResponse getBillingAccountInfoByAccountNumber(@PathVariable String accountNumber) {
        return billingAccountService.getBillingAccountInfoByAccountNumber(accountNumber);
    }
}