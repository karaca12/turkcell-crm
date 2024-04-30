package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.client.OrderServiceClient;
import com.turkcell.pair1.invoiceservice.client.ProductServiceClient;
import com.turkcell.pair1.invoiceservice.core.business.paging.PageInfo;
import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.repository.AccountRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.*;
import com.turkcell.pair1.invoiceservice.service.mapper.AccountMapper;
import com.turkcell.pair1.invoiceservice.service.rules.AccountBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final AccountBusinessRules businessRules;

    @Override
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByIsDeletedFalseAndAccountNumber(accountNumber);
    }

    @Override
    public AccountDto getAccountDtoByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByIsDeletedFalseAndAccountNumber(accountNumber).orElseThrow(/*TODO*/);
        return AccountMapper.INSTANCE.accountToAccountDto(account);
    }

    @Override
    public boolean isActive(String accountNumber) {
        Optional<Account> account = getAccountByAccountNumber(accountNumber);
        return (account.isPresent() && !account.get().isDeleted());
    }

    @Override
    public List<GetCustomerAccountsResponse> getCustomerAccountsByCustomerId(String customerId, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        List<GetCustomerAccountsResponse> responses = AccountMapper.INSTANCE.getCustomerAccountResponseFromAccount(accountRepository.findByCustomerId(customerId, pageable));
        businessRules.convertToAccountType(responses);
        return responses;
    }

    @Override
    public List<GetAccountProductResponse> getProductsForAccount(String accountNumber) {
        List<GetAccountOrderResponse> orders = orderServiceClient.findOrdersByAccountNumber(accountNumber);
        Set<Integer> productIds = orders.stream()
                .flatMap(order -> order.getOrderItems().stream())
                .map(GetOrderItemResponse::getProductId)
                .collect(Collectors.toSet());

        return productIds.stream()
                .map(productServiceClient::getProductById)
                .collect(Collectors.toList());
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void updateAccountById(Integer id) {
        accountRepository.updateAccountById(id);
    }

    @Override
    @Transactional
    public BasketItem addItemToBasket(AddItemToBasketRequest request) {
        Account account = getAccountByAccountNumber(request.getAccountNumber()).orElseThrow(() -> new IllegalStateException("Account not found."));
        Basket basket = account.getBasket();
        return basketService.addBasketItem(basket, request.getProductId(), request.getQuantity());
    }

    @Override
    public void clearBasket(String accountNumber) { // TODO: business rules??
        Account account = getAccountByAccountNumber(accountNumber).orElseThrow();
        basketService.clearBasket(account);
    }

    @Override
    public GetDetailedAccountProductResponse getDetailedAccountProduct(int productId, String orderId) {
        GetDetailedAccountProductResponse productDetail = productServiceClient.getProductDetailById(productId);
        GetAccountOrderResponse order = orderServiceClient.getOrderById(orderId);
        productDetail.setServiceAddress(order.getAddress());
        //TODO:find the primary address
        productDetail.setServiceStartDate(order.getServiceStartDate());

        productDetail.setProductSpecId(determineProductSpecId(order.getOrderItems(), productId));

        return productDetail;//TODO:prodchar gelicek nasil olucak allah bilir
    }

    @Override
    public String getCustomerIdByAccountNumber(String accountNumber) {
        Account account = businessRules.getAccountFromOptional(accountRepository.findByAccountNumber(accountNumber));
        return account.getCustomerId();
    }


    @Override
    public String generateAccountNumber() {
        return businessRules.generateAccountNumber();
    }

    private String determineProductSpecId(List<GetOrderItemResponse> orderItems, int productId) {
        for (GetOrderItemResponse orderItem : orderItems) {
            if (orderItem.getProductId() == productId) {
                return orderItem.getSpecId();
            }
        }
        return null;

    }
}