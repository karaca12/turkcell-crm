package com.turkcell.pair1.invoiceservice.entity;

import com.turkcell.pair1.invoiceservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "baskets")
public class Basket extends BaseEntity {

    @OneToOne(mappedBy = "basket")
    private Account account;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketItem> basketItems = new ArrayList<>();
}
