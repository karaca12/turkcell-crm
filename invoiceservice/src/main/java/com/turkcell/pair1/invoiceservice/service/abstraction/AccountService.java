package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.core.business.paging.PageInfo;
import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetDetailedAccountProductResponse;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountById(Integer accountId);

    AccountDto getAccountDtoById(Integer accountId);

    BasketItem addItemToBasket(AddItemToBasketRequest request);

    void clearBasket(Integer accountId);

    boolean isActive(Integer accountId);

    List<GetAccountProductResponse> getProductsForAccount(Integer accountId);

    Account save(Account account);

    void updateAccountById(Integer id);

    List<GetCustomerAccountsResponse> getCustomerAccountsByCustomerId(String customerId, PageInfo pageInfo);

    GetDetailedAccountProductResponse getDetailedAccountProduct(int productId, String orderId);

    String getCustomerIdByAccountNumber(String accountNumber);
}
