package com.turkcell.pair1.customerservice.service.dto.request;

import com.turkcell.pair1.customerservice.core.service.constants.Messages;
import com.turkcell.pair1.customerservice.service.validation.annotation.NationalityId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    String firstName;
    String middleName;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    String lastName;
    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    LocalDate birthDate;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    String gender;
    String fatherName;
    String motherName;
    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    @NationalityId(message = Messages.ValidationErrors.NATIONALITY_ID)
    String nationalityId;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Email(message = Messages.ValidationErrors.EMAIL)
    String email;
    String homePhone;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    String mobilePhone;
    String fax;
    @NotEmpty(message = Messages.ValidationErrors.NOT_EMPTY)
    List<@Valid AddAddressToCustomerRequest> addressList;
}