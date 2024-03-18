package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.repository.AddressRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
