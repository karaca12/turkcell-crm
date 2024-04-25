package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.client.OrderServiceClient;
import com.turkcell.pair1.invoiceservice.client.ProductServiceClient;
import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.repository.AccountRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountOrderResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetOrderItemResponse;
import com.turkcell.pair1.invoiceservice.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BasketService basketService;

    private final ProductServiceClient productServiceClient;
    private final OrderServiceClient orderServiceClient;
    @Override
    public Optional<Account> getAccountById(Integer id) {
        return accountRepository.findByIsDeletedFalseAndId(id);
    }

    @Override
    public AccountDto getAccountDtoById(Integer accountId) {
        Account account = accountRepository.findByIsDeletedFalseAndId(accountId).orElseThrow(/*TODO*/);
        return AccountMapper.INSTANCE.accountToAccountDto(account);
    }

    @Override
    public boolean isActive(Integer accountId) {
        Optional<Account> account = getAccountById(accountId);
        return (account.isPresent() && !account.get().isDeleted());
    }

    @Override
    public List<GetCustomerAccountsResponse> getCustomerAccountsByCustomerId(String customerId) {
        return AccountMapper.INSTANCE.getCustomerInfoResponsesFromCustomers(accountRepository.findByIsDeletedFalseAndCustomerId(customerId));
    }

    @Override
    public List<GetAccountProductResponse> getProductsForAccount(int accountId) {
        List<GetAccountOrderResponse> orders = orderServiceClient.findOrdersByAccountId(accountId);
        Set<Integer> productIds = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .map(GetOrderItemResponse::getProductId)
                .collect(Collectors.toSet());

        return productIds.stream()
                .map(productServiceClient::getProductById)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BasketItem addItemToBasket(AddItemToBasketRequest request) {
        Account account = getAccountById(request.getAccountId()).orElseThrow(() -> new IllegalStateException("Account not found."));
        Basket basket = account.getBasket();

        return basketService.addBasketItem(basket, request.getProductId(), request.getQuantity());
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
