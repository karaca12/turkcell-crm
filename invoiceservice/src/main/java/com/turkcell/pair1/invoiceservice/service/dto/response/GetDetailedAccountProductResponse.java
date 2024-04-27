package com.turkcell.pair1.invoiceservice.service.dto.response;


import com.turkcell.pair1.invoiceservice.entity.Address;
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


    private Long productOfferId;


    private Long productSpecId;

    private Address serviceAddress;
    private LocalDate serviceStartDate;


    private String prodChars;
}
