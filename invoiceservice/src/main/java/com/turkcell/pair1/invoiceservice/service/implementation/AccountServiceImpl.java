package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.repository.AccountRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddItemToBasketRequest;
import com.turkcell.pair1.invoiceservice.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final BasketService basketService;
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
