package com.turkcell.pair1.customerservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Customer customer;

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

}