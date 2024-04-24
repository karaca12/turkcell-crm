package com.turkcell.pair1.invoiceservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor // TODO: implement this
public class AccountDto {
    private String accountNumber;
    private boolean isDeleted;
}
