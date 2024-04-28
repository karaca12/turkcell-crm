package com.turkcell.pair1.productservice.repository;

import com.turkcell.pair1.productservice.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {
}
