package com.turkcell.pair1.productservice.repository;


import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.dto.request.SearchProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.SearchProductResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByIsDeletedFalseAndProductOfferId(String productOfferId);

    @Query("select new com.turkcell.pair1.productservice.service.dto.response." +
            "SearchProductResponse(p.id,p.productOfferId,p.productOfferName) " +
            "from Product p " +
            "where (:#{#request.productOfferId} is null or p.productOfferId like %:#{#request.productOfferId}%) " +
            "and (:#{#request.productOfferName} is null or p.productOfferName like %:#{#request.productOfferName}%)" +
            "and p.isDeleted=false ")
    List<SearchProductResponse> search(@Param("request") SearchProductRequest request, Pageable pageable);
}
