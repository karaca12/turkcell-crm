package com.turkcell.pair1.productservice.repository;

import com.turkcell.pair1.productservice.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {
}
