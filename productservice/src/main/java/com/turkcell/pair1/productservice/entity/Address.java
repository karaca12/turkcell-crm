package com.turkcell.pair1.productservice.entity;

import com.turkcell.pair1.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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
}
