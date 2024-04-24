package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Address;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.repository.AddressRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AddressService;
import com.turkcell.pair1.invoiceservice.service.abstraction.StreetService;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.invoiceservice.service.mapper.AddressMapper;
import com.turkcell.pair1.invoiceservice.service.rules.AddressBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        for (AddAddressToAccountRequest addressRequest :
                request) {
            Address address = AddressMapper.INSTANCE.addAddressToAccountRequestToAddress(addressRequest);
            address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(addressRequest.getStreetName(), addressRequest.getCity()));
            address.setAccounts(account);
            response.add(AddressMapper.INSTANCE.getAddressResponseFromAddress(addressRepository.save(address)));

        }
        return response;
    }

    @Override
    @Transactional
    public void updateAddressForBillingAccount(BillingAccount billingAccount, List<UpdateAddressRequest> addressList) {
        for (UpdateAddressRequest addressRequest: addressList ){
            Address address = AddressMapper.INSTANCE.updateAddressRequestToAddress(addressRequest);
            address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(addressRequest.getStreetName(), addressRequest.getCity()));
            address.setAccounts(billingAccount.getAccount());
            addressRepository.updateAddressById(address,addressRequest.getUpdatedId());
        }

    }
}
