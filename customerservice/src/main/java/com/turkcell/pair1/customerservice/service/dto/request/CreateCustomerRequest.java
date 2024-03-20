package com.turkcell.pair1.customerservice.service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest{
    @NotNull
    String firstName;
    String middleName;
    @NotNull
    String lastName;
    @NotNull
    LocalDate birthDate;
    @NotNull
    String gender;
    String fatherName;
    String motherName;
    @NotNull
    Integer nationalityId;
    @NotNull
    String email;
    String homePhone;
    @NotNull
    String mobilePhone;
    String fax;
}