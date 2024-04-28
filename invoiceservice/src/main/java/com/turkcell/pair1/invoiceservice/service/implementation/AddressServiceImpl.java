package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Address;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.repository.AddressRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AddressService;
import com.turkcell.pair1.invoiceservice.service.abstraction.StreetService;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateAddressToBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.invoiceservice.service.mapper.AddressMapper;
import com.turkcell.pair1.invoiceservice.service.rules.AddressBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final StreetService streetService;
    private final AddressRepository addressRepository;
    private final AddressBusinessRules businessRules;

    @Override
    public List<GetAddressResponse> addAddressesForAccount(List<AddAddressToAccountRequest> request, Account account) {
        List<GetAddressResponse> response = new ArrayList<>();

        for (int i = 0; i < request.size(); i++) {
            AddAddressToAccountRequest addressRequest = request.get(i);
            Address address = AddressMapper.INSTANCE.addAddressToAccountRequestToAddress(addressRequest);
            if (i == 0) {
                address.setIsPrimary(true);
            }
            address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(addressRequest.getStreetName(), addressRequest.getCity()));
            address.setAccounts(account);
            response.add(AddressMapper.INSTANCE.getAddressesResponseFromAddress(addressRepository.save(address)));
        }
        return response;
    }

    @Override
    @Transactional
    public void updateAddressForBillingAccount(BillingAccount billingAccount, UpdateAddressRequest updatedAddress) {
        businessRules.billingAccountMustContainAddress(billingAccount.getAccount(), updatedAddress.getUpdatedId());
        Address address = AddressMapper.INSTANCE.updateAddressRequestToAddress(updatedAddress);
        address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(updatedAddress.getStreetName(), updatedAddress.getCity()));
        address.setAccounts(billingAccount.getAccount());
        addressRepository.updateAddressById(address, updatedAddress.getUpdatedId());
    }

    @Override
    public List<GetAddressResponse> getAddressesFromBillingAccountByBillingAccountId(BillingAccount billingAccount, Pageable pageable) {
        return AddressMapper.INSTANCE.getAddressResponsesFromAddresses(addressRepository.findByIsDeletedFalseAndAccounts(billingAccount.getAccount(), pageable));

    }

    @Override
    public CreateAddressToBillingAccountResponse addAddressForAccount(AddAddressToAccountRequest request, BillingAccount billingAccount) {
        Address address = AddressMapper.INSTANCE.addAddressToAccountRequestToAddress(request);
        address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(request.getStreetName(), request.getCity()));
        address.setIsPrimary(false);
        address.setAccounts(billingAccount.getAccount());
        return AddressMapper.INSTANCE.getAddressResponseFromAddress(addressRepository.save(address));
    }

    @Override
    public void deleteAddressById(Integer addressId, BillingAccount billingAccount) {
        businessRules.billingAccountMustContainAddress(billingAccount.getAccount(), addressId);
        businessRules.accountShouldNotHaveOneAddressForDelete(billingAccount.getAccount());
        Address address = businessRules.getAddressFromOptional(addressRepository.findById(addressId));
        businessRules.deletedAddressCannotBePrimary(address);
        address.setDeleted(true);
        address.setDeletedAt(LocalDateTime.now());
        addressRepository.save(address);
    }

    @Override
    public GetAddressResponse setPrimaryAddressById(Integer addressId, BillingAccount billingAccount) {
        businessRules.billingAccountMustContainAddress(billingAccount.getAccount(), addressId);
        Address address = businessRules.getAddressFromOptional(addressRepository.findById(addressId));
        businessRules.checkIfAddressIsAlreadyAPrimaryAddress(address);

        for (Address searchedAddress : billingAccount.getAccount().getAddresses()) {
            if (searchedAddress.getIsPrimary()) {
                searchedAddress.setIsPrimary(false);
                addressRepository.save(searchedAddress);
            }
        }
        address.setIsPrimary(true);
        return AddressMapper.INSTANCE.getAddressesResponseFromAddress(addressRepository.save(address));
    }

    @Override
    public void deletedAddressesWhenDeletingBillingAccounts(BillingAccount billingAccount) {
        for (Address address : billingAccount.getAccount().getAddresses()) {
            address.setDeleted(true);
            address.setDeletedAt(LocalDateTime.now());
            addressRepository.save(address);
        }
    }
}