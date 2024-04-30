package com.turkcell.pair1.productservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductResponse {
    private Integer id;
    private String productOfferId;
    private String productOfferName;
}
