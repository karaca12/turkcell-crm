package com.turkcell.pair1.productservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.productservice.entity.Product;
import com.turkcell.pair1.productservice.repository.ProductRepository;
import com.turkcell.pair1.productservice.service.dto.response.SearchProductResponse;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductBusinessRules {
    private final MessageService messageService;
    private final ProductRepository productRepository;


    public void checkIfSearchIsEmpty(List<SearchProductResponse> response) {
        if (response.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_PRODUCT_FOUND));
        }
    }

    public Product getProductFromOptional(Optional<Product> optionalProduct) {
        return optionalProduct.orElseThrow(() -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_PRODUCT_FOUND)));
    }

    public Product findByIsDeletedFalseAndProductOfferId(String productOfferId) {
        return productRepository.findByIsDeletedFalseAndProductOfferId(productOfferId).orElseThrow(
                () -> new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_PRODUCT_FOUND))
        );
    }
}
