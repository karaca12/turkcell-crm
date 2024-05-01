package com.turkcell.pair1.customerservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "individual_customers")
public class IndividualCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @NotNull
    @Column(name = "nationality_id", nullable = false,length = 11)
    private String  nationalityId;

    @Column(name = "home_phone")
    private String homePhone;

    @NotNull
    @Column(name = "mobile_phone", nullable = false)
    private String mobilePhone;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}