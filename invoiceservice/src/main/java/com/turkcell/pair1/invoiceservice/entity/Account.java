package com.turkcell.pair1.invoiceservice.entity;

import com.turkcell.pair1.invoiceservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @OneToMany(mappedBy = "accounts")
    private List<Address> addresses;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false) /*TODO: nullable olabilir*/
    private String description;

    @ColumnDefault(value = "false")
    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "account_number")
    private String accountNumber;

    private String customerId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private Basket basket;
}