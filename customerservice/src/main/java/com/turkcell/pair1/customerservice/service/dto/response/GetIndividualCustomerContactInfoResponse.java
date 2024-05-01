package com.turkcell.pair1.customerservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerContactInfoResponse {
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String fax;
}