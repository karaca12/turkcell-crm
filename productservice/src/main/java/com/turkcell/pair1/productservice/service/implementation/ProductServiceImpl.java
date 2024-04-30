package com.turkcell.pair1.productservice.service.implementation;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.productservice.core.business.paging.PageInfo;
import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.repository.ProductRepository;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.*;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import com.turkcell.pair1.productservice.service.dto.response.SearchProductResponse;
import com.turkcell.pair1.productservice.service.mapper.ProductMapper;
import com.turkcell.pair1.productservice.service.rules.ProductBusinessRules;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final MessageService messageService;
    private final ProductBusinessRules businessRules;

    @Override
    public boolean hasActiveProducts(String customerId) {
        return false; // No Active Products -> Safe to Delete Customer
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findByIsDeletedFalseAndId(productId).orElseThrow(
                () -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_PRODUCT_FOUND))
        );
    }

    @Override
    public List<ProductDtoResponse> getProductsByCatalogueId(Integer catalogueId) {
        List<Product> products = productRepository.findByCatalogueId(catalogueId);
        List<ProductDtoResponse> productDtoResponses = new ArrayList<>();
        for (Product product : products) {
            ProductDtoResponse productDtoResponse = ProductMapper.INSTANCE.productDtoResponseFromProduct(product);
            productDtoResponses.add(productDtoResponse);
        }
        return productDtoResponses;
    }

    @Override
    public GetAccountProductResponse getAccountProductById(int id) {
        return ProductMapper.INSTANCE.accountProductDtoFromProduct(getProductById(id));
    }

    @Override
    public List<SearchProductResponse> searchProducts(SearchProductRequest request, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        List<SearchProductResponse> response = productRepository.search(request, pageable);
        businessRules.checkIfSearchIsEmpty(response);
        return response;
    }


    @Override
    public double getProductPriceByOfferId(String productOfferId) {
        return businessRules.getProductFromOptional(productRepository.findByProductOfferId(productOfferId)).getProductPrice();
    }


    @Override
    public GetDetailedAccountProductResponse getDetailedProduct(int id) {
        return ProductMapper.INSTANCE.getDetailedProductFromProduct(productRepository.getProductById(id));
    }
}
