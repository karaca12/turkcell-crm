package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateAddressToBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressService {
    List<GetAddressResponse> addAddressesForAccount(List<AddAddressToAccountRequest> request, Account account);

    void updateAddressForBillingAccount(BillingAccount billingAccount, UpdateAddressRequest updatedAddress);

    List<GetAddressResponse> getAddressesFromBillingAccountByBillingAccountId(BillingAccount billingAccount, Pageable pageable);

    CreateAddressToBillingAccountResponse addAddressForAccount(AddAddressToAccountRequest request, BillingAccount billingAccount);

    void deleteAddressById(Integer addressId, BillingAccount billingAccount);

    GetAddressResponse setPrimaryAddressById(Integer addressId, BillingAccount billingAccount);

    void deletedAddressesWhenDeletingBillingAccounts(BillingAccount billingAccount);
}
