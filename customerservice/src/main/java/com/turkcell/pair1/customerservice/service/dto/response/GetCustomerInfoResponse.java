package com.turkcell.pair1.customerservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerInfoResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String fatherName;
    private String motherName;
    private Integer nationalityId;
}
