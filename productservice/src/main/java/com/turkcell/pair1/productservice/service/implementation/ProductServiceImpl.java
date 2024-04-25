package com.turkcell.pair1.productservice.service.implementation;

import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.repository.ProductRepository;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public boolean hasActiveProducts(String customerId) {
        return false; // No Active Products -> Safe to Delete Customer
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
