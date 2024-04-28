package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.invoiceservice.client.CustomerServiceClient;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.repository.BillingAccountRepository;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class BillingAccountBusinessRules {
    private final BillingAccountRepository billingAccountRepository;
    private final MessageService messageService;
    private final CustomerServiceClient customerServiceClient;

    private static final Random random = new Random();


    public BillingAccount getBillingAccountFromOptional(Optional<BillingAccount> optionalBillingAccount) {
        return optionalBillingAccount.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ACCOUNT_FOUND)));
    }

    public void ensureBillingAccountHasNoActiveProducts(BillingAccount billingAccount) {
        //TODO: implement after product service is implemented
    }

    public String generateAccountNumber() {
        String accountNumber;
        accountNumber = generateUniqueAccountNumber();
        if (!isUniqueNumber(accountNumber)) {
            return accountNumber;
        } else {
            return generateAccountNumber();
        }
    }

    private String generateUniqueAccountNumber() {
        StringBuilder accountNumberBuilder = new StringBuilder();
        accountNumberBuilder.append(random.nextInt(5) + 1);
        for (int i = 0; i < 6; i++) {
            accountNumberBuilder.append(random.nextInt(10));
        }
        return accountNumberBuilder.toString();
    }

    private boolean isUniqueNumber(String accountNumber) {
        return billingAccountRepository.existsByAccountNumber(accountNumber);
    }

    public void checkIfCustomerExists(String customerId) {
        if (!customerServiceClient.checkByCustomerIdIfCustomerExists(customerId)){
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_CUSTOMER_FOUND));
        }
    }
}