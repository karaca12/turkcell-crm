package com.turkcell.pair1.invoiceservice.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressToAccountRequest {
//    @NotBlank
    private String city;
//    @NotBlank
    private String  streetName;
//    @NotNull
//    @Min(value = 0)
    private Integer flatNumber;
//    @NotBlank
    private String description;
}
