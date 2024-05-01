package com.turkcell.pair1.invoiceservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountDtoByAccountNumberResponse {
    private String accountNumber;
    private boolean isDeleted;
}