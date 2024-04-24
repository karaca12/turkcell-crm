package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;

import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Integer accountId);
    AccountDto getAccountDtoById(Integer accountId);
    void saveAccount(Account account);
    BasketItem addItemToBasket(AddItemToBasketRequest request);
    boolean isActive(Integer accountId);

}
