package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.repository.AccountRepository;
import com.turkcell.pair1.invoiceservice.repository.BillingAccountRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AddressService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BillingAccountService;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.mapper.AccountMapper;
import com.turkcell.pair1.invoiceservice.service.mapper.BillingAccountMapper;
import com.turkcell.pair1.invoiceservice.service.rules.BillingAccountBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BillingAccountServiceImpl implements BillingAccountService {
    private final AccountRepository accountRepository;
    private final BillingAccountRepository billingAccountRepository;
    private final AddressService addressService;
    private final BillingAccountBusinessRules billingAccountBusinessRules;
    private final BasketService basketService;

    @Override
    public CreateBillingAccountResponse create(CreateBillingAccountRequest request) {
        Basket basket = basketService.createBasket();
        Account account = AccountMapper.INSTANCE.getAccountFromCreateRequest(request);
        Account savedAccount = accountRepository.save(account);
        BillingAccount billingAccount = BillingAccountMapper.INSTANCE.getBillingAccountFromCreateRequest(request);
        billingAccount.setAccount(savedAccount);
        billingAccount.getAccount().setBasket(basket);
        BillingAccount savedBillingAccount = billingAccountRepository.save(billingAccount);
        CreateBillingAccountResponse response = BillingAccountMapper.INSTANCE.getCreateBillingAccountResponseFromBillingAccount(savedBillingAccount);
        response.setAddressList(addressService.addAddressesForAccount(request.getAddressList(), savedAccount));
        return response;
    }

    @Transactional
    @Override
    public void updateBillingAccountByBillingAccountId(Integer billingAccountId, UpdateBillingAccountRequest request) {
        BillingAccount billingAccount = billingAccountBusinessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_IsDeletedFalseAndId(billingAccountId));
        billingAccountRepository.updateBillingAccountById(billingAccount.getId(), request);
        addressService.updateAddressForBillingAccount(billingAccount, request.getAddressList());
    }
}
