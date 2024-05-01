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
    public GetAccountProductResponse getAccountProductByOfferId(String productOfferId) {
        return ProductMapper.INSTANCE.accountProductDtoFromProduct(businessRules.findByIsDeletedFalseAndProductOfferId(productOfferId));
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
        return businessRules.getProductFromOptional(productRepository.findByIsDeletedFalseAndProductOfferId(productOfferId)).getProductPrice();
    }


    @Override
    public GetDetailedAccountProductResponse getDetailedProduct(String productOfferId) {
        return ProductMapper.INSTANCE.getDetailedProductFromProduct(productRepository.findByIsDeletedFalseAndProductOfferId(productOfferId).orElseThrow());
    }

    @Override
    public boolean checkByProductOfferIdIfProductExists(String productOfferId) {
        return productRepository.existsByProductOfferId(productOfferId);
    }
}
