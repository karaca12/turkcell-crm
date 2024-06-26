package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountInfoRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateAddressToBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetBillingAccountInfoResponse;
import com.turkcell.pair1.paging.PageInfo;

import java.util.List;

public interface BillingAccountService {
    CreateBillingAccountResponse create(CreateBillingAccountRequest request);

    void updateBillingAccountInfoByAccountNumber(String accountNumber, UpdateBillingAccountInfoRequest billingAccountRequest);

    void deleteBillingAccountByAccountNumber(String accountNumber);

    List<GetAddressResponse> getBillingAccountAddressesByAccountNumber(String accountNumber, PageInfo pageInfo);

    CreateAddressToBillingAccountResponse createAddressToBillingAccountByAccountNumber(String accountNumber, AddAddressToAccountRequest request);

    void deleteAddressByAccountNumberAndAddressId(String accountNumber, Integer addressId);

    GetAddressResponse setPrimaryAddressByAccountNumberAndAddressId(String accountNumber, Integer addressId);

    void updateBillingAccountAddressByAccountNumber(String accountNumber, UpdateAddressRequest request);

    GetBillingAccountInfoResponse getBillingAccountInfoByAccountNumber(String accountNumber);
}