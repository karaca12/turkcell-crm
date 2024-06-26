package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.invoiceservice.client.CustomerServiceClient;
import com.turkcell.pair1.invoiceservice.client.OrderServiceClient;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BillingAccountBusinessRules {
    private final MessageService messageService;
    private final CustomerServiceClient customerServiceClient;
    private final OrderServiceClient orderServiceClient;

    public BillingAccount getBillingAccountFromOptional(Optional<BillingAccount> optionalBillingAccount) {
        return optionalBillingAccount.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ACCOUNT_FOUND)));
    }

    public void ensureBillingAccountHasNoActiveProducts(BillingAccount billingAccount) {
        if (orderServiceClient.accountHasActiveProducts(billingAccount.getAccount().getAccountNumber()).isHasActiveProducts()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.ACCOUNT_HAS_ACTIVE_PRODUCT));
        }
    }

    public void checkIfCustomerExists(String customerId) {
        if (!customerServiceClient.checkByCustomerIdIfCustomerExists(customerId)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND));
        }
    }
}