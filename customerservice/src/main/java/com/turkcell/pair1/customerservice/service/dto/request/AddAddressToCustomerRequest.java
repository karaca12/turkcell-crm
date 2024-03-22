package com.turkcell.pair1.customerservice.service.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressToCustomerRequest {
    private String city;
    private String  street;
    private Integer flatNumber;
    private String description;
}
