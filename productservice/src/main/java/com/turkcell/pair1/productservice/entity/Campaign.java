package com.turkcell.pair1.productservice.entity;

import com.turkcell.pair1.productservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "campaigns")
public class Campaign extends BaseEntity {

    @Column(name = "offer_id",nullable = false)
    private String offerId;

    @Column(name = "offer_name",nullable = false)
    private String offerName;

    @Column(name = "product_price_discount_percentage",nullable = false)
    private double productPriceDiscountPercentage;

    @Column(name = "start_date",nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date",nullable = false)
    private LocalDate endDate;

    @OneToMany(mappedBy = "campaign")
    List<Product> products;
}
