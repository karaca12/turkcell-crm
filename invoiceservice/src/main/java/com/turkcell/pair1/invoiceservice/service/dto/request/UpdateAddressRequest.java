package com.turkcell.pair1.invoiceservice.service.dto.request;

import com.turkcell.pair1.message.Messages;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAddressRequest {
    @NotNull
    private Integer updatedId;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String city;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String  streetName;
    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    @Min(value = 0,message = Messages.ValidationErrors.MIN)
    private Integer flatNumber;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String description;
}