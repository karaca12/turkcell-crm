package com.turkcell.pair1.productservice.service.abstraction;

import com.turkcell.pair1.productservice.core.business.paging.PageInfo;
import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.dto.request.SearchProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import com.turkcell.pair1.productservice.service.dto.response.SearchProductResponse;

import java.util.List;

public interface ProductService {
    GetAccountProductResponse getAccountProductByOfferId(String productOfferId);

    Product getProductByOfferId(String productOfferId);

    List<SearchProductResponse> searchProducts(SearchProductRequest request, PageInfo pageInfo);

    double getProductPriceByOfferId(String productOfferId);

    GetDetailedAccountProductResponse getDetailedProduct(String productOfferId);

    boolean checkByProductOfferIdIfProductExists(String productOfferId);
}
