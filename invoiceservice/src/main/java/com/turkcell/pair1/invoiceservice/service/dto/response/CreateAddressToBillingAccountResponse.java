package com.turkcell.pair1.invoiceservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressToBillingAccountResponse {
    private Integer id;
    private String city;
    private String street;
    private Integer flatNumber;
    private String description;
}