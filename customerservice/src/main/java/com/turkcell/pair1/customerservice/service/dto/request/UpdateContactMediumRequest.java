package com.turkcell.pair1.customerservice.service.dto.request;

import com.turkcell.pair1.customerservice.core.service.constants.Messages;
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
public class UpdateContactMediumRequest {
    @NotNull
    private Integer updatedId;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String email;
    private String homePhone;
    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    private String mobilePhone;
    private String fax;
}
