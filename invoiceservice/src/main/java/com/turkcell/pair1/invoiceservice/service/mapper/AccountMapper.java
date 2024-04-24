package com.turkcell.pair1.invoiceservice.service.mapper;

import com.turkcell.pair1.invoiceservice.entity.Account;
import com.turkcell.pair1.invoiceservice.service.dto.AccountDto;
import com.turkcell.pair1.invoiceservice.service.dto.request.CreateBillingAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto accountToAccountDto(Account account);

    @Mapping(source = "addressList", target = "addresses")
    Account getAccountFromCreateRequest(CreateBillingAccountRequest request);
}