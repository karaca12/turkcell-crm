package com.turkcell.pair1.productservice.entity;

import com.turkcell.pair1.productservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_attributes")
public class ProductAttribute extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;
}