package com.turkcell.pair1.productservice.service.mapper;


import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.GetDetailedAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.CatalogueProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);
    CatalogueProductResponse productDtoResponseFromProduct(Product product);
    @Mapping(source = "campaign.offerId",target = "campaignId")
    @Mapping(source = "campaign.offerName",target = "campaignName")
    GetAccountProductResponse accountProductDtoFromProduct(Product product);
    GetDetailedAccountProductResponse getDetailedProductFromProduct(Product product);
}
