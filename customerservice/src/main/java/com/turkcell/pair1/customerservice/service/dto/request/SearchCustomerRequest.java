package com.turkcell.pair1.customerservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCustomerRequest {
    private String nationalityId;
    private String customerId;
    private String accountNumber;
    private String mobilePhone;
    private String firstName;
    private String lastName;
    private String orderNumber;
}
