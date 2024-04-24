package com.turkcell.pair1.invoiceservice.controller;

import com.turkcell.pair1.invoiceservice.service.abstraction.BillingAccountService;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("updateBillingAccountByBillingAccountId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBillingAccountByBillingAccountId(@PathVariable Integer id, @RequestBody @Valid UpdateBillingAccountRequest request) {
        billingAccountService.updateBillingAccountByBillingAccountId(id, request);
    }
}
