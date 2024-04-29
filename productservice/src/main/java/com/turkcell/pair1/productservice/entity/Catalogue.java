package com.turkcell.pair1.productservice.entity;

import com.turkcell.pair1.productservice.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "catalogues")
public class Catalogue extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "catalogue")
    private List<Product> products;
}
