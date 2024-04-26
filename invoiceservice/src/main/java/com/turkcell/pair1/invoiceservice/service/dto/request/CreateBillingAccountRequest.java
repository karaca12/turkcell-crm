package com.turkcell.pair1.invoiceservice.service.dto.request;

import com.turkcell.pair1.message.Messages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBillingAccountRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String name;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String description;
    @NotEmpty(message = Messages.ValidationErrors.NOT_EMPTY)
    List<@Valid AddAddressToAccountRequest> addressList;
}