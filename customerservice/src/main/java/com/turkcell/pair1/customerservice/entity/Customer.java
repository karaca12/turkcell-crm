package com.turkcell.pair1.customerservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "customer_id", length = Integer.MAX_VALUE)
    private String customerId;

    @NotNull
    @Column(name = "first_name", nullable = false, length = Integer.MAX_VALUE)
    private String firstName;

    @Column(name = "middle_name", length = Integer.MAX_VALUE)
    private String middleName;

    @NotNull
    @Column(name = "last_name", nullable = false, length = Integer.MAX_VALUE)
    private String lastName;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "gender", nullable = false, length = Integer.MAX_VALUE)
    private String gender;

    @Column(name = "father_name", length = Integer.MAX_VALUE)
    private String fatherName;

    @Column(name = "mother_name", length = Integer.MAX_VALUE)
    private String motherName;

    @NotNull
    @Column(name = "nationality_id", nullable = false)
    private Integer nationalityId;

    @Column(name = "account_number", length = Integer.MAX_VALUE)
    private String accountNumber;

    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "home_phone", length = Integer.MAX_VALUE)
    private String homePhone;

    @NotNull
    @Column(name = "mobile_phone", nullable = false, length = Integer.MAX_VALUE)
    private String mobilePhone;

    @Column(name = "fax", length = Integer.MAX_VALUE)
    private String fax;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

}