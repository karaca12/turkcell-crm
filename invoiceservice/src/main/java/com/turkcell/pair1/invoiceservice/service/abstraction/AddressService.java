package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;

import java.util.List;

public interface AddressService {
    List<GetAddressResponse> addAddressesForAccount(List<AddAddressToAccountRequest> request, Account account);

    void updateAddressForBillingAccount(BillingAccount billingAccount, List<UpdateAddressRequest> addressList);
}
