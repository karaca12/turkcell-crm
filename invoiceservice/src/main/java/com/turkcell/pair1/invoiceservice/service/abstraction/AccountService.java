package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Integer accountId);

    AccountDto getAccountDtoById(Integer accountId);

    void saveAccount(Account account);

    BasketItem addItemToBasket(AddItemToBasketRequest request);

    void clearBasket(Integer accountId);

    boolean isActive(Integer accountId);

    List<GetAccountProductResponse> getProductsForAccount(int accountId);

    Account save(Account account);

    void updateAccountById(Integer id);

    List<GetCustomerAccountsResponse> getCustomerAccountsByCustomerId(String customerId);
}
