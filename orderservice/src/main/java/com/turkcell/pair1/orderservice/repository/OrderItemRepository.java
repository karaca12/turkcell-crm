package com.turkcell.pair1.orderservice.repository;

import com.turkcell.pair1.orderservice.entity.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    OrderItem findByProductOfferId(String productOfferId);
}
