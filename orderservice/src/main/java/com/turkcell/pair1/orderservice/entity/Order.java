package com.turkcell.pair1.orderservice.entity;

import com.turkcell.pair1.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(value = "orders")
public class Order {
    @Id
    private String id;
    private Date orderDate;
    private String customerId;
    private double totalPrice;
}
