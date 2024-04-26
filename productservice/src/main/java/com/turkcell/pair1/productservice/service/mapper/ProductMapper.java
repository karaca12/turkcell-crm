package com.turkcell.pair1.productservice.service.mapper;


import com.turkcell.pair1.productservice.entity.Campaign;
import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.service.dto.request.AddProductRequest;
import com.turkcell.pair1.productservice.service.dto.response.GetAccountProductResponse;
import com.turkcell.pair1.productservice.service.dto.response.ProductDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);
    Product productFromAddDto(AddProductRequest productAddDto);
    ProductDtoResponse productDtoResponseFromProduct(Product product);
    @Mapping(source = "id",target = "productId")
    @Mapping(source = "campaign.id",target = "campaignId")
    @Mapping(source = "campaign.name",target = "campaignName")
    GetAccountProductResponse accountProductDtoFromProduct(Product product);

}
