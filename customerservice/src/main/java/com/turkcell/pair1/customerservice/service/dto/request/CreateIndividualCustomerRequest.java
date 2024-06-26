package com.turkcell.pair1.customerservice.service.dto.request;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.customerservice.service.validation.annotation.CreateMobilePhone;
import com.turkcell.pair1.customerservice.service.validation.annotation.Gender;
import com.turkcell.pair1.customerservice.service.validation.annotation.NationalityId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 1, max = 50, message = Messages.ValidationErrors.LENGTH)
    String firstName;
    @Length(min = 1, max = 50, message = Messages.ValidationErrors.LENGTH)
    String middleName;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Length(min = 1, max = 50, message = Messages.ValidationErrors.LENGTH)
    String lastName;
    @NotNull(message = Messages.ValidationErrors.NOT_NULL)
    LocalDate birthDate;
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    @Gender
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
    @CreateMobilePhone(message = Messages.ValidationErrors.INVALID_MOBILE_PHONE)
    @NotBlank(message = Messages.ValidationErrors.NOT_BLANK)
    String mobilePhone;
    String fax;
    @NotEmpty(message = Messages.ValidationErrors.NOT_EMPTY)
    List<@Valid AddAddressToCustomerRequest> addressList;
}