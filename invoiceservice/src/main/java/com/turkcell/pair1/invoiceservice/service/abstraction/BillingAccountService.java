package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;

public interface BillingAccountService {
    CreateBillingAccountResponse create(CreateBillingAccountRequest request);

    void updateBillingAccountByBillingAccountId(Integer billingAccountId, UpdateBillingAccountRequest billingAccountRequest);

}
