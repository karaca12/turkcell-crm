package com.turkcell.pair1.customerservice.service.dto.request;

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
public class AddUpdateAndDeleteAddressRequest {
    private List<@Valid AddAddressToCustomerRequest> newAddresses;
    private List<@Valid UpdateAddressRequest> updatedAddresses;
    private List<Integer> deletedIds;
}
