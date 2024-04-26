package com.turkcell.pair1.invoiceservice.service.mapper;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto accountToAccountDto(Account account);


    @Mapping(source = "billingAccount.status", target = "status")
    @Mapping(source = "billingAccount.accountNumber", target = "accountNumber")
    @Mapping(source = "billingAccount.name", target = "name")
    GetCustomerAccountsResponse getCustomerInfoResponseFromCustomer(Account account);

    @Mapping(source = "addressList", target = "addresses")
    Account getAccountFromCreateRequest(CreateBillingAccountRequest request);

    default List<GetCustomerAccountsResponse> getCustomerInfoResponsesFromCustomers(List<Account> accounts) {
        return accounts.stream()
                .map(this::getCustomerInfoResponseFromCustomer)
                .collect(Collectors.toList());
    }
}