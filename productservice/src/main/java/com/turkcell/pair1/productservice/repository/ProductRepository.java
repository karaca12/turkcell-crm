package com.turkcell.pair1.productservice.repository;


import com.turkcell.pair1.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByIsDeletedFalseAndId(Integer productId);

    List<Product> findByCatalogueId(Integer catalogueId);
    Product getProductById(int id);
    List<Product> findByProductOfferIdAndProductOfferNameContaining(String productOfferId, String productOfferName);
    List<Product> findByProductOfferId(String productOfferId);
    List<Product> findByProductOfferNameContaining(String productOfferName);
}
