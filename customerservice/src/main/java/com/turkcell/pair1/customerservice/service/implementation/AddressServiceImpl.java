package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.AddressRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.customerservice.service.mapper.AddressMapper;
import com.turkcell.pair1.customerservice.service.rules.AddressBusinessRules;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<GetAddressResponse> addAddressesForCustomer(List<AddAddressToCustomerRequest> addressRequests, Customer customer) {

        List<GetAddressResponse> response = new ArrayList<>();

        for (AddAddressToCustomerRequest addressRequest :
                addressRequests) {
            Address address = AddressMapper.INSTANCE.addAddressToCustomerRequestToAddress(addressRequest);
            address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(addressRequest.getStreetName(), addressRequest.getCity()));
            address.setCustomer(customer);
            response.add(AddressMapper.INSTANCE.getAddressResponseFromAddress(addressRepository.save(address)));

        }
        return response;
    }

    @Override
    public List<GetAddressResponse> getAddressesFromCustomerByCustomerId(Customer customer) {
        return AddressMapper.INSTANCE.getAddressResponsesFromAddresses(addressRepository.findByIsDeletedFalseAndCustomer(customer));
    }

    @Override
    public void deleteAddressesFromIds(List<Integer> deletedIds,Customer customer) {
        for (Integer id :
                deletedIds) {
            businessRules.customerMustContainAddress(customer,id);
            Address address = businessRules.getAddressFromOptional(addressRepository.findById(id));
            address.setDeleted(true);
            address.setDeletedAt(LocalDateTime.now());
        }
    }

    @Override
    @Transactional
    public void updateAddressesForCustomer(List<UpdateAddressRequest> updatedAddresses, Customer customer) {
        for (UpdateAddressRequest updatedAddress :
                updatedAddresses) {
            businessRules.customerMustContainAddress(customer,updatedAddress.getUpdatedId());
            Address address = AddressMapper.INSTANCE.updateAddressRequestToAddress(updatedAddress);
            address.setStreet(streetService.findStreetByNameAndCityAndIsDeletedFalse(updatedAddress.getStreetName(), updatedAddress.getCity()));
            address.setCustomer(customer);
            addressRepository.updateAddressById(address,updatedAddress.getUpdatedId());
        }
    }

}
