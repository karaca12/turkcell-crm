package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Address;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressBusinessRules {
    private final MessageService messageService;
    public void billingAccountMustContainAddress(Account account, Integer id) {
        boolean contains = false;
        for (Address address : account.getAddresses()) {
            if (address.getId().equals(id)) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.ACCOUNT_DOES_NOT_CONTAIN_ADDRESS));
        }
    }

    public void accountShouldNotHaveOneAddressForDelete(Account account) {
        if (account.getAddresses().size() == 1) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.ACCOUNT_HAS_ONLY_ONE_ADDRESS));
        }
    }

    public Address getAddressFromOptional(Optional<Address> optionalAddress) {
        return optionalAddress.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ADDRESS_FOUND)));
    }

    public void deletedAddressCannotBePrimary(Address address) {
        if (address.isPrimary()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.CANNOT_DELETE_PRIMARY_ADDRESS));

        }
    }

    public void checkIfAddressIsAlreadyAPrimaryAddress(Address address) {
        if (address.isPrimary()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.ADDRESS_ALREADY_IS_PRIMARY));

        }
    }
}
