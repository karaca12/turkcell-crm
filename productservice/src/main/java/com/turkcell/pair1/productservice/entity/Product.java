package com.turkcell.pair1.productservice.entity;

import com.turkcell.pair1.productservice.core.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Column(name = "product_name")
    private String productName;

    @Column(name = "campaign_id")
    private Long campaignId;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "product_offer_name")
    private String productOfferName;

    @Column(name = "product_offer_id")
    private Long productOfferId;

    @Column(name = "product_spec_id")
    private Long productSpecId;

    @Column(name = "service_start_date")
    private LocalDate serviceStartDate;

    @Column(name = "prod_chars")
    private String prodChars;

    @ManyToOne
    @JoinColumn(name = "service_address_id")
    private Address serviceAddress;
}
