package com.turkcell.pair1.customerservice.service.implementation;

import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.customerservice.repository.AddressRepository;
import com.turkcell.pair1.customerservice.service.abstraction.AddressService;
import com.turkcell.pair1.customerservice.service.abstraction.StreetService;
import com.turkcell.pair1.customerservice.service.dto.request.AddAddressToCustomerRequest;
import com.turkcell.pair1.customerservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.customerservice.service.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final StreetService streetService;



    @Override
    public List<GetAddressResponse> addAddressesForCustomer(List<AddAddressToCustomerRequest> addressRequests, Customer customer) {

        List<GetAddressResponse> response=new ArrayList<>();

        for (AddAddressToCustomerRequest addressRequest :
                addressRequests) {
            Address address = AddressMapper.INSTANCE.addAddressToCustomerRequestToAddress(addressRequest);
            address.setStreet(streetService.findStreetByNameAndCity(addressRequest.getStreetName(), addressRequest.getCity()));
            address.setCustomer(customer);
            response.add(AddressMapper.INSTANCE.getAddressResponseFromAddress(addressRepository.save(address)));

        }
        return response;
    }

}
