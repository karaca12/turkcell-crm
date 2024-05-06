package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.repository.AccountRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AddressService;
import com.turkcell.pair1.invoiceservice.service.dto.response.CheckAccountForOrderResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetOrderItemResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.OrderProductSpecResponse;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AccountBusinessRules {
    private final MessageService messageService;
    private final AccountRepository accountRepository;
    private final AddressService addressService;
    private static final Random random = new Random();

    public Account getAccountFromOptional(Optional<Account> optionalAccount) {
        return optionalAccount.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_ACCOUNT_FOUND)));
    }

    public void convertToAccountType(List<GetCustomerAccountsResponse> responses) {
        for (GetCustomerAccountsResponse response : responses) {
            if ((int) response.getAccountNumber().charAt(0) - '0' <= 5) {
                response.setType("Billing Account");
            } else {
                response.setType(null);
            }
        }
    }

    public String generateAccountNumber() {
        String accountNumber;
        accountNumber = generateUniqueAccountNumber();
        if (!isAccountNumberExist(accountNumber)) {
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

    private boolean isAccountNumberExist(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }


    public CheckAccountForOrderResponse checkIfAccountExistsAndGetAddress(String accountNumber, Integer addressId) {
            Account account = getAccountFromOptional(accountRepository.findByAccountNumber(accountNumber));
            return addressService.getAddressFromId(account,addressId);
    }
    public OrderProductSpecResponse determineProductSpecs(List<GetOrderItemResponse> orderItems, String productOfferId) {
        for (GetOrderItemResponse orderItem : orderItems) {
            if (orderItem.getProductOfferId().equals(productOfferId) ) {
                return orderItem.getProductSpec();
            }
        }
        return null;

    }
}