package com.turkcell.pair1.productservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatalogueProductResponse {
    private Integer id;
    private String productOfferName;
    private String productOfferId;
}
