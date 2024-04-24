package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BasketItem;
import com.turkcell.pair1.invoiceservice.repository.AccountRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
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
    public BasketItem addItemToBasket(Integer accountId, Integer productId, int quantity) {
        Account account = getAccountById(accountId).orElseThrow(() -> new IllegalStateException("Account not found."));

        Basket basket = account.getBasket();
        if (basket == null) {
            basket = new Basket();
            basket.setAccount(account);  // Set both sides of the relationship
            account.setBasket(basket);   // Only need to save the account once
            accountRepository.save(account); // Save changes once, cascading should handle the rest
        } else {
            // This part is only needed if there's something specific to update on existing baskets
            //basketRepository.save(basket);
        }

        return basketService.addBasketItem(basket, productId, quantity);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
