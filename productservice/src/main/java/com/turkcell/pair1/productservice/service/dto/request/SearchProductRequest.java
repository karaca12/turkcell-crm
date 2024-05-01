package com.turkcell.pair1.productservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductRequest {
    private String productOfferId;
    private String productOfferName;
}
