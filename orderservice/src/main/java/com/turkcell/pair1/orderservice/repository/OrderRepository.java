package com.turkcell.pair1.orderservice.repository;

import com.turkcell.pair1.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByAccountNumber(String accountNumber);

}