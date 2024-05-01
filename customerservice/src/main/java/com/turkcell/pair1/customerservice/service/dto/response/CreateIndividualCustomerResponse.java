package com.turkcell.pair1.customerservice.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerResponse {
    String customerId;
    String firstName;
    String middleName;
    String lastName;
    LocalDate birthDate;
    String gender;
    String fatherName;
    String motherName;
    String nationalityId;
    String email;
    String homePhone;
    String mobilePhone;
    String fax;
    List<GetAddressResponse> addressList;
}