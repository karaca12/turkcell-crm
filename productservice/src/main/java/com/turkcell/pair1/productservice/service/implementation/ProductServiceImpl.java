package com.turkcell.pair1.productservice.service.implementation;

import com.turkcell.pair1.paging.PageInfo;
import com.turkcell.pair1.productservice.entity.Campaign;
import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.repository.ProductRepository;
import com.turkcell.pair1.productservice.service.abstraction.ProductService;
import com.turkcell.pair1.productservice.service.dto.request.SearchProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.SearchProductResponse;
import com.turkcell.pair1.productservice.service.mapper.ProductMapper;
import com.turkcell.pair1.productservice.service.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductBusinessRules businessRules;

    @Override
    public GetAccountProductResponse getAccountProductByOfferId(String productOfferId) {
        return ProductMapper.INSTANCE.accountProductDtoFromProduct(businessRules.findByIsDeletedFalseAndProductOfferId(productOfferId));
    }

    @Override
    public double getProductPriceByOfferId(String productOfferId) {
        Product product=businessRules.getProductFromOptional(productRepository.findByIsDeletedFalseAndProductOfferId(productOfferId));
        double discount=product.getCampaign().getProductPriceDiscountPercentage();
        return product.getProductPrice()-(product.getProductPrice()*(discount/100));
    }

    @Override
    public GetDetailedAccountProductResponse getDetailedProduct(String productOfferId) {
        return ProductMapper.INSTANCE.getDetailedProductFromProduct(businessRules.getProductFromOptional(productRepository.findByIsDeletedFalseAndProductOfferId(productOfferId)));
    }

    @Override
    public boolean checkByProductOfferIdIfProductExists(String productOfferId) {
        return productRepository.existsByProductOfferId(productOfferId);
    }
    @Override
    public List<SearchProductResponse> searchProducts(SearchProductRequest request, PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getSize());
        List<SearchProductResponse> response = productRepository.search(request, pageable);
        businessRules.checkIfSearchIsEmpty(response);
        return response;
    }
}
