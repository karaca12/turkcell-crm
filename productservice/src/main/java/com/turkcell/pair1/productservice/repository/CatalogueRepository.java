package com.turkcell.pair1.productservice.repository;

import com.turkcell.pair1.productservice.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {
    Optional<Catalogue> findByIsDeletedFalseAndId(Integer catalogueId);
}
