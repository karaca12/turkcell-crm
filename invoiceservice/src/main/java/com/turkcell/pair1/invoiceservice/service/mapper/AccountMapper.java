package com.turkcell.pair1.invoiceservice.service.mapper;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.service.dto.GetAccountDtoByAccountNumberResponse;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import com.turkcell.pair1.invoiceservice.service.dto.response.GetCustomerAccountsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    GetAccountDtoByAccountNumberResponse accountToAccountDto(Account account);

    @Mapping(source = "accountNumber", target = "accountNumber")
    @Mapping(target = "type", ignore = true)
    GetCustomerAccountsResponse getCustomerAccountResponseFromAccount(Account account);

    @Mapping(source = "addressList", target = "addresses")
    Account getAccountFromCreateRequest(CreateBillingAccountRequest request);

    List<GetCustomerAccountsResponse> getCustomerAccountResponseFromAccount(List<Account> account);
}