package com.turkcell.pair1.productservice.service.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetDetailedAccountProductResponse {

    private String productOfferName;


    private Long productOfferId;


    private Long productSpecId;


    private String prodChars;
}