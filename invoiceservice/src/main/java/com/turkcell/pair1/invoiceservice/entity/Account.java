package com.turkcell.pair1.invoiceservice.entity;

import com.turkcell.pair1.invoiceservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(mappedBy = "account")
    private BillingAccount billingAccount;

    @OneToMany(mappedBy = "accounts")
    private List<Address> addresses;

    @OneToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    private String customerId;
}