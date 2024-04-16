package com.turkcell.pair1.productservice.repository;


import com.turkcell.pair1.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
