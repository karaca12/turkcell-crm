package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AddressBusinessRules {
    public void billingAccountMustContainAddress(Account account, Integer id) {
        boolean contains = false;
        for (Address address : account.getAddresses()) {
            if (address.getId().equals(id)) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            throw new RuntimeException();
        }
    }

    public void accountShouldNotHaveOneAddressForDelete(Account account) {
        if (account.getAddresses().size() == 1) {
            throw new RuntimeException(); //TODO
        }
    }

    public Address getAddressFromOptional(Optional<Address> optionalAddress) {
        return optionalAddress.orElseThrow(() -> new RuntimeException()); //TODO
    }

    public void deletedAddressCannotBePrimary(Address address) {
        if (address.getIsPrimary()) {
            throw new RuntimeException();//TODO
        }
    }

    public void checkIfAddressIsAlreadyAPrimaryAddress(Address address) {
        if (address.getIsPrimary()) {
            throw new RuntimeException(); //TODO
        }
    }
}
