package com.turkcell.pair1.invoiceservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerAccountsResponse {
    private boolean isDeleted;
    private String accountNumber;
    private String name;
    private String type;
}