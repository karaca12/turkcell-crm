package com.turkcell.pair1.customerservice.service.dto.request;

import com.turkcell.pair1.customerservice.service.validation.annotation.NationalityId;
import com.turkcell.pair1.message.Messages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerInfoRequest {
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
}
