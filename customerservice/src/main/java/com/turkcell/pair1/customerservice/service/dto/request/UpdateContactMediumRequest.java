package com.turkcell.pair1.customerservice.service.dto.request;

import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import jakarta.validation.constraints.Email;
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
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Email(message = Messages.ValidationErrors.EMAIL)
    private String email;
    private String homePhone;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    private String mobilePhone;
    private String fax;
}
