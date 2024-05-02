package com.turkcell.pair1.invoiceservice.service.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDetailedAccountProductResponse {
    private String productOfferName;
    private String productOfferId;
    private String productSpecId;
    private GetServiceAddressResponse serviceAddress;
    private LocalDate serviceStartDate;
}