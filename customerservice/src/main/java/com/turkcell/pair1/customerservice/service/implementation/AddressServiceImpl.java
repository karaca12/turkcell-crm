package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.AddressRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.mapper.AddressMapper;
import org.springframework.stereotype.Service;

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
    public void addAddressesForCustomer(List<AddAddressToCustomerRequest> addressRequests, Customer customer) {
        for (AddAddressToCustomerRequest addressRequest :
                addressRequests) {
            Address address = AddressMapper.INSTANCE.addAddressToCustomerRequesttoAddress(addressRequest);
            address.setStreet(streetService.findStreetByNameAndCity(addressRequest.getStreetName(), addressRequest.getCity()));
            address.setCustomer(customer);
            addressRepository.save(address);
        }
    }

}
