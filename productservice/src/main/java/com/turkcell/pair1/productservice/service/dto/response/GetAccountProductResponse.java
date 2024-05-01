package com.turkcell.pair1.productservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountProductResponse {
    private String productOfferId;
    private String productOfferName;
    private int campaignId;
    private String campaignName;
}
