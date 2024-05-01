package com.turkcell.pair1.invoiceservice.entity;

import com.turkcell.pair1.invoiceservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "basket_items")
public class BasketItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Column(name = "product_offer_id", nullable = false)
    private String productOfferId;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
