package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.repository.BillingAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BillingAccountBusinessRules {
    private final BillingAccountRepository billingAccountRepository;

    public BillingAccount getBillingAccountFromOptional(Optional<BillingAccount> optionalBillingAccount) {
        return optionalBillingAccount.orElseThrow();
    }

    public void ensureBillingAccountHasNoActiveProducts(BillingAccount billingAccount) {
        //TODO: implement after product service is implemented
    }
}
