package com.turkcell.pair1.invoiceservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {

    private Integer updatedId;

    private String city;

    private String streetName;


    private Integer flatNumber;

    private String description;
}
