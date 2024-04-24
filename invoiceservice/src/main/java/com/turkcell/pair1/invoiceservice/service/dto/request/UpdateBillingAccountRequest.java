package com.turkcell.pair1.invoiceservice.service.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBillingAccountRequest {
    private String name;
    private String description;
    List<@Valid UpdateAddressRequest> addressList;
}
