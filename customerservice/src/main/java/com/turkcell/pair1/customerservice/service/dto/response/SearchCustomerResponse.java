package com.turkcell.pair1.customerservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCustomerResponse {

    private String customerId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalityId;
}
