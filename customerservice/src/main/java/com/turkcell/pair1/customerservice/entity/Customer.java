package com.turkcell.pair1.customerservice.entity;

import com.turkcell.pair1.customerservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Column(name = "customer_id")
    private String customerId;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "fax")
    private String fax;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToOne(mappedBy = "customer")
    private IndividualCustomer individualCustomer;
}