package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.repository.BillingAccountRepository;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BillingAccountBusinessRules {
    private final BillingAccountRepository billingAccountRepository;
    private final MessageService messageService;


    public BillingAccount getBillingAccountFromOptional(Optional<BillingAccount> optionalBillingAccount) {
        return optionalBillingAccount.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ACCOUNT_FOUND)));
    }

    public void ensureBillingAccountHasNoActiveProducts(BillingAccount billingAccount) {
        //TODO: implement after product service is implemented
    }
}
