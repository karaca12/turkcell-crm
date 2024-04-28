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
@Table(name = "internet_services")
public class InternetService extends Product {
    @Column(name = "pstn_no")
    private String pstnNo;

    @Column(name = "xdsl_username")
    private String xdslUsername;

    @Column(name = "bandwidth")
    private String bandwidth;

    @Column(name = "xdsl_no")
    private String xdslNo;

    @Column(name = "xdsl_password")
    private String xdslPassword;
}
