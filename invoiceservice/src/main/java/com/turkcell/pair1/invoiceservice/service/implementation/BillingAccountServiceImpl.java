package com.turkcell.pair1.invoiceservice.service.implementation;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.entity.Basket;
import com.turkcell.pair1.invoiceservice.entity.BillingAccount;
import com.turkcell.pair1.invoiceservice.repository.BillingAccountRepository;
import com.turkcell.pair1.invoiceservice.service.abstraction.AccountService;
import com.turkcell.pair1.invoiceservice.service.abstraction.AddressService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BasketService;
import com.turkcell.pair1.invoiceservice.service.abstraction.BillingAccountService;
import com.turkcell.pair1.invoiceservice.service.dto.request.AddAddressToAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateAddressRequest;
import com.turkcell.pair1.invoiceservice.service.dto.request.UpdateBillingAccountInfoRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateAddressToBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.CreateBillingAccountResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetAddressResponse;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetBillingAccountInfoResponse;
import com.turkcell.pair1.invoiceservice.service.mapper.AccountMapper;
import com.turkcell.pair1.invoiceservice.service.mapper.BillingAccountMapper;
import com.turkcell.pair1.invoiceservice.service.rules.BillingAccountBusinessRules;
import com.turkcell.pair1.paging.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingAccountServiceImpl implements BillingAccountService {
    private final BillingAccountRepository billingAccountRepository;
    private final AddressService addressService;
    private final BillingAccountBusinessRules businessRules;
    private final BasketService basketService;
    private final AccountService accountService;

    @Transactional
    @Override
    public CreateBillingAccountResponse create(CreateBillingAccountRequest request) {
        businessRules.checkIfCustomerExists(request.getCustomerId());
        Basket basket = basketService.createBasket();
        Account account = AccountMapper.INSTANCE.getAccountFromCreateRequest(request);
        account.setAccountNumber(accountService.generateAccountNumber());
        Account savedAccount = accountService.save(account);
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
    public void updateBillingAccountInfoByAccountNumber(String accountNumber, UpdateBillingAccountInfoRequest request) {
        BillingAccount billingAccount = businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumberAndAccount_IsDeletedFalse(accountNumber));
        billingAccountRepository.updateBillingAccountById(billingAccount.getId(), request);
        accountService.updateAccountById(billingAccount.getAccount().getId());
    }

    @Override
    public void deleteBillingAccountByAccountNumber(String accountNumber) {
        BillingAccount billingAccount = businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumberAndAccount_IsDeletedFalse(accountNumber));
        businessRules.ensureBillingAccountHasNoActiveProducts(billingAccount);
        billingAccount.getAccount().setDeleted(true);
        billingAccount.getAccount().setDeletedAt(LocalDateTime.now());
        addressService.deletedAddressesWhenDeletingBillingAccounts(billingAccount);
        billingAccountRepository.save(billingAccount);
    }

    @Override
    public List<GetAddressResponse> getBillingAccountAddressesByAccountNumber(String accountNumber, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        return addressService.getAddressesFromBillingAccountByBillingAccountId(businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumber(accountNumber)), pageable);
    }

    @Override
    public CreateAddressToBillingAccountResponse createAddressToBillingAccountByAccountNumber(String accountNumber, AddAddressToAccountRequest request) {
        BillingAccount billingAccount = businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumberAndAccount_IsDeletedFalse(accountNumber));
        return addressService.addAddressForAccount(request, billingAccount);
    }

    @Override
    public void deleteAddressByAccountNumberAndAddressId(String accountNumber, Integer addressId) {
        BillingAccount billingAccount = businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumberAndAccount_IsDeletedFalse(accountNumber));
        addressService.deleteAddressById(addressId, billingAccount);
    }

    @Override
    public GetAddressResponse setPrimaryAddressByAccountNumberAndAddressId(String accountNumber, Integer addressId) {
        BillingAccount billingAccount = businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumberAndAccount_IsDeletedFalse(accountNumber));
        return addressService.setPrimaryAddressById(addressId, billingAccount);
    }

    @Override
    public void updateBillingAccountAddressByAccountNumber(String accountNumber, UpdateAddressRequest request) {
        BillingAccount billingAccount = businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumberAndAccount_IsDeletedFalse(accountNumber));
        addressService.updateAddressForBillingAccount(billingAccount, request);
    }

    @Override
    public GetBillingAccountInfoResponse getBillingAccountInfoByAccountNumber(String accountNumber) {
        BillingAccount billingAccount = businessRules.getBillingAccountFromOptional(billingAccountRepository.findByAccount_AccountNumberAndAccount_IsDeletedFalse(accountNumber));
        return BillingAccountMapper.INSTANCE.getBillingAccountInfoFromBillingAccount(billingAccount);
    }
}