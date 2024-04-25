package com.turkcell.pair1.invoiceservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBillingAccountInfoRequest {
    private String name;
    private String description;
}
