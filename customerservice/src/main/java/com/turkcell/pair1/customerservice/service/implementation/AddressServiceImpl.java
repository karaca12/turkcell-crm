package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.entity.Street;
import com.turkcell.pair1.customerservice.repository.AddressRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.request.CreateCustomerRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final StreetService streetService;

    public AddressServiceImpl(AddressRepository addressRepository, StreetService streetService) {
        this.addressRepository = addressRepository;
        this.streetService = streetService;
    }

    @Override
    public void saveList(List<Address> addresses) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public List<Address> createAddressesForCustomer(CreateCustomerRequest request, Customer customer) {
        List<Address> addresses = new ArrayList<>();
        for (AddAddressToCustomerRequest addressRequest :
                request.getAddressList()) {
            Address address = new Address();
            address.setDescription(addressRequest.getDescription());
            address.setFlatNumber(addressRequest.getFlatNumber());
            address.setStreet(streetService.findStreetByNameAndCity(addressRequest.getStreet(),addressRequest.getCity()));
            address.setCustomer(customer);
            addresses.add(address);
        }
        customer.setAddresses(addresses);
        return addresses;
    }
}
