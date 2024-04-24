package com.turkcell.pair1.invoiceservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "billing_accounts")
public class BillingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;


    @Column(name = "name")
    private String name;


    @Column(name = "description")
    private String description;


    @Column(name = "status")
    private String status;

    @Column(name = "account_number")
    private String accountNumber;

    private String customerId;
}
