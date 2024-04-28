package com.turkcell.pair1.productservice.service.dto.request;

import com.turkcell.pair1.productservice.service.dto.ProductAttributeDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductConfigurationRequest {
    private Integer productId;
    private List<ProductAttributeDto> attributes;
}