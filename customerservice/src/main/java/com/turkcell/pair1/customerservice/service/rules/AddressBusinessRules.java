package com.turkcell.pair1.customerservice.service.rules;

import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.customerservice.entity.Address;
import com.turkcell.pair1.customerservice.entity.Customer;
import com.turkcell.pair1.message.Messages;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressBusinessRules {
    private final MessageService messageService;

    public void customerMustContainAddress(Customer customer, Integer id) {
        boolean contains = false;
        for (Address address : customer.getAddresses()) {
            if (address.getId().equals(id)) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CUSTOMER_DOES_NOT_CONTAIN_ADDRESS));
        }
    }

    public Address getAddressFromOptional(Optional<Address> optionalAddress) {
        return optionalAddress.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ADDRESS_FOUND)));
    }

    public void customerShouldNotHaveOneAddressForDelete(Customer customer) {
        if (customer.getAddresses().size() == 1) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CUSTOMER_HAS_ONLY_ONE_ADDRESS));
        }
    }

    public void deletedAddressCannotBePrimary(Address address) {
        if (address.getIsPrimary()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CANNOT_DELETE_PRIMARY_ADDRESS));
        }
    }

    public void checkIfAddressIsAlreadyAPrimaryAddress(Address address) {
        if (address.getIsPrimary()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.ADDRESS_ALREADY_IS_PRIMARY));
        }
    }
}
