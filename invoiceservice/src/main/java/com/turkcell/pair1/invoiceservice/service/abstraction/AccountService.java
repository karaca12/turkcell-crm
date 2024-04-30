package com.turkcell.pair1.invoiceservice.service.abstraction;

import com.turkcell.pair1.invoiceservice.core.business.paging.PageInfo;
import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CheckAccountForOrderResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetDetailedAccountProductResponse;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> getAccountByAccountNumber(String accountNumber);

    AccountDto getAccountDtoByAccountNumber(String accountNumber);

    BasketItem addItemToBasket(AddItemToBasketRequest request);

    void clearBasket(String accountNumber);

    boolean isActive(String accountNumber);

    List<GetAccountProductResponse> getProductsForAccount(String accountNumber);

    Account save(Account account);

    void updateAccountById(Integer id);

    List<GetCustomerAccountsResponse> getCustomerAccountsByCustomerId(String customerId, PageInfo pageInfo);

    GetDetailedAccountProductResponse getDetailedAccountProduct(int productId, String orderId);

    String getCustomerIdByAccountNumber(String accountNumber);

    String generateAccountNumber();

    CheckAccountForOrderResponse checkIfAccountExistsAndGetAddress(String accountNumber, Integer addressId);

}