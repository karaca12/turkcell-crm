package com.turkcell.pair1.orderservice.entity;

import com.turkcell.pair1.orderservice.core.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(value = "orders")
public class Order extends BaseEntity {
    private String accountNumber;
    private List<OrderItem> items;
    //TODO:customerId account id ile account tarafindan cekilecek.
    private String customerId;
    private double totalPrice;
    private LocalDate serviceStartDate;
    private Address serviceAddress;
}