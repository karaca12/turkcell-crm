package com.turkcell.pair1.invoiceservice.service.dto.request;

import com.turkcell.common.message.Messages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBillingAccountInfoRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String name;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String description;
}