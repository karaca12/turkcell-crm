package com.turkcell.pair1.productservice.service.implementation;

import com.turkcell.pair1.productservice.entity.ProductAttribute;
import com.turkcell.pair1.productservice.repository.ProductAttributeRepository;
import com.turkcell.pair1.productservice.service.abstraction.ProductAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ProductAttributeServiceImpl implements ProductAttributeService {
    private final ProductAttributeRepository productAttributeRepository;
    @Override
    public void save(ProductAttribute productAttribute) {
        productAttributeRepository.save(productAttribute);
    }
}
