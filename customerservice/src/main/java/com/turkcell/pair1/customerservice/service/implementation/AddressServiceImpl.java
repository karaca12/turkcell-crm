package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.AddressRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.customerservice.service.dto.response.CreateAddressToCustomerResponse;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.customerservice.service.mapper.AddressMapper;
import com.turkcell.pair1.customerservice.service.rules.AddressBusinessRules;
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
    private final AddressRepository addressRepository;
    private final StreetService streetService;
    private final AddressBusinessRules businessRules;


    @Override
    @Transactional
    public List<GetAddressResponse> addAddressesForCustomer(List<AddAddressToCustomerRequest> addressRequests, Customer customer) {

        List<GetAddressResponse> response = new ArrayList<>();

        for (int i = 0; i < addressRequests.size(); i++) {
            AddAddressToCustomerRequest addressRequest = addressRequests.get(i);
            Address address = AddressMapper.INSTANCE.addAddressToCustomerRequestToAddress(addressRequest);
            address.setPrimary(i == 0);
            address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(addressRequest.getStreetName(), addressRequest.getCity()));
            address.setCustomer(customer);
            response.add(AddressMapper.INSTANCE.getAddressResponseFromAddress(addressRepository.save(address)));
        }
        return response;
    }

    @Override
    public List<GetAddressResponse> getAddressesFromCustomerByCustomerId(Customer customer, Pageable pageable) {
        return AddressMapper.INSTANCE.getAddressResponsesFromAddresses(addressRepository.findByIsDeletedFalseAndCustomer(customer, pageable));
    }

    @Override
    @Transactional
    public void updateAddressForCustomer(UpdateAddressRequest updatedAddress, Customer customer) {
        businessRules.customerMustContainAddress(customer, updatedAddress.getUpdatedId());
        Address address = AddressMapper.INSTANCE.updateAddressRequestToAddress(updatedAddress);
        address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(updatedAddress.getStreetName(), updatedAddress.getCity()));
        address.setCustomer(customer);
        addressRepository.updateAddressById(address, updatedAddress.getUpdatedId());
    }

    @Override
    @Transactional
    public CreateAddressToCustomerResponse addAddressForCustomer(AddAddressToCustomerRequest request, Customer customer) {
        Address address = AddressMapper.INSTANCE.addAddressToCustomerRequestToAddress(request);
        address.setPrimary(false);
        address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(request.getStreetName(), request.getCity()));
        address.setCustomer(customer);
        return AddressMapper.INSTANCE.getCreateAddressResponseFromAddress(addressRepository.save(address));
    }

    @Transactional
    @Override
    public void deleteAddressById(Integer addressId, Customer customer) {
        businessRules.customerMustContainAddress(customer, addressId);
        businessRules.customerShouldNotHaveOneAddressForDelete(customer);
        Address address = businessRules.getAddressFromOptional(addressRepository.findById(addressId));
        businessRules.deletedAddressCannotBePrimary(address);
        address.setDeleted(true);
        address.setDeletedAt(LocalDateTime.now());
        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void setPrimaryAddressById(Integer addressId, Customer customer) {
        businessRules.customerMustContainAddress(customer, addressId);
        Address address = businessRules.getAddressFromOptional(addressRepository.findByIdAndIsDeletedFalse(addressId));
        businessRules.checkIfAddressIsAlreadyAPrimaryAddress(address);

        for (Address searchedAddress : customer.getAddresses()) {
            if (searchedAddress.isPrimary()) {
                searchedAddress.setPrimary(false);
                addressRepository.save(searchedAddress);
            }
        }
        address.setPrimary(true);
        addressRepository.save(address);
    }

    @Override
    public void deleteAddressesWhenDeletingCustomer(Customer customer) {
        for (Address address : customer.getAddresses()) {
            address.setDeleted(true);
            address.setDeletedAt(LocalDateTime.now());
            addressRepository.save(address);
        }
    }
}
