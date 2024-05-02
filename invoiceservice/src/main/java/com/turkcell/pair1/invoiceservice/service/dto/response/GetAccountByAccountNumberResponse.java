package com.turkcell.pair1.invoiceservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountByAccountNumberResponse {
    private String accountNumber;
    private boolean isDeleted;
}