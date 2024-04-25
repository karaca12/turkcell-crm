package com.turkcell.pair1.productservice.repository;


import com.turkcell.pair1.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product getProductById(int id);
}
