package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.pair1.invoiceservice.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountBusinessRules {
    public Account getAccountFromOptional(Optional<Account> optionalAccount) {
        return optionalAccount.orElseThrow(() -> new RuntimeException()); //TODO
    }
}
