package com.turkcell.pair1.productservice.service.implementation;

import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.repository.ProductRepository;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public boolean hasActiveProducts(String customerId) {
        return false; // No Active Products -> Safe to Delete Customer
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findByIsDeletedFalseAndId(productId).orElseThrow();
    }

    @Override
    public List<ProductDtoResponse> getProductsByCatalogueId(Integer catalogueId) {
        List<Product> products = productRepository.findByCatalogueId(catalogueId);
        List<ProductDtoResponse> productDtoResponses = new ArrayList<>();
        for (Product product : products) {
            ProductDtoResponse productDtoResponse = new ProductDtoResponse(product.getProductName(),product.getId());
            productDtoResponses.add(productDtoResponse);
        }
        return productDtoResponses;
    }

    @Override
    public void add(AddProductRequest productAddDto) {
        Product product= ProductMapper.INSTANCE.productFromAddDto(productAddDto);
        productRepository.save(product);

    }

    @Override
    public GetAccountProductResponse getProductById(int id) {
        return ProductMapper.INSTANCE.accountProductDtoFromProduct(productRepository.getProductById(id));
    }

}
