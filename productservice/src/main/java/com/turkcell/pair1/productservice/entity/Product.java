package com.turkcell.pair1.productservice.entity;

import com.turkcell.pair1.productservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "product_offer_id")
    private String productOfferId;

    @Column(name = "product_offer_name")
    private String productOfferName;

    @Column(name = "product_price")
    private double productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogue_id")
    private Catalogue catalogue;



}