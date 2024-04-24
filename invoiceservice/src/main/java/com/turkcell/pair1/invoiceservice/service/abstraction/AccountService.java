package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;

import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Integer accountId);
    AccountDto getAccountDtoById(Integer accountId);
    void saveAccount(Account account);
    BasketItem addItemToBasket(Integer accountId, Integer productId, int quantity);
    boolean isActive(Integer accountId);

}
