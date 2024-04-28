package com.turkcell.pair1.productservice.entity.product;

import com.turkcell.pair1.productservice.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "modems")
public class Modem extends Product {
    @Column(name = "brand")
    private String brand; //SN

    @Column(name = "serial_number")
    private String serialNumber; //SN

    @Column(name = "model")
    private String model;
}
