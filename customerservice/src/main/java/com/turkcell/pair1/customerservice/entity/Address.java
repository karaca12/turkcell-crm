package com.turkcell.pair1.customerservice.entity;

import com.turkcell.pair1.customerservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "street_id", nullable = false)
    private Street street;

    @NotNull
    @Column(name = "flat_number", nullable = false)
    private Integer flatNumber;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @ColumnDefault(value = "false")
    @Column(name = "isPrimary")
    private boolean isPrimary;

}