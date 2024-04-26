package com.turkcell.pair1.productservice.repository;

import com.turkcell.pair1.productservice.entity.Catalogue;
import com.turkcell.pair1.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {
}
