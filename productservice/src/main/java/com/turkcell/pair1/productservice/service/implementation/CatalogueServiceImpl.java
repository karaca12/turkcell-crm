package com.turkcell.pair1.productservice.service.implementation;

import com.turkcell.pair1.productservice.entity.Catalogue;
import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.repository.CatalogueRepository;
import com.turkcell.pair1.productservice.service.abstraction.CatalogueService;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.response.CatalogueProductResponse;
import com.turkcell.pair1.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogueServiceImpl implements CatalogueService {
    private final CatalogueRepository catalogueRepository;
    private final ProductService productService;


    @Override
    public List<CatalogueProductResponse> getCatalogueProducts(Integer catalogueId) {
        Catalogue catalogue = catalogueRepository.findById(catalogueId).orElseThrow();
        List<Product> products = catalogue.getProducts();
        List<CatalogueProductResponse> catalogueProductResponse = new ArrayList<>();
        for (Product product : products) {
            catalogueProductResponse.add(ProductMapper.INSTANCE.productDtoResponseFromProduct(product));
        }
        return catalogueProductResponse;
    }
}

