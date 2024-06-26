package com.turkcell.pair1.invoiceservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillingAccountResponse {
    private String accountNumber;
    private String customerId;
    private String name;
    private String description;
    List<GetAddressResponse> addressList;
}