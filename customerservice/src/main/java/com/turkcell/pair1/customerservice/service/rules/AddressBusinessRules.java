package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.pair1.customerservice.core.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.service.abstraction.MessageService;
import com.turkcell.pair1.service.constants.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressBusinessRules {
    private final MessageService messageService;

    public void customerMustContainAddress(Customer customer, Integer id) {
        boolean contains=false;
        for (Address address : customer.getAddresses()) {
            if (address.getId().equals(id)) {
                contains = true;
                break;
            }
        }
        if (!contains){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CUSTOMER_DOES_NOT_CONTAIN_ADDRESS));
        }
    }

    public Address getAddressFromOptional(Optional<Address> optionalAddress) {
        return optionalAddress.orElseThrow(()-> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ADDRESS_FOUND)));
    }
}
