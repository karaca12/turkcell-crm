package com.turkcell.pair1.invoiceservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.invoiceservice.client.ProductServiceClient;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasketBusinessRules {
    private final MessageService messageService;
    private final ProductServiceClient productServiceClient;

    public void checkIfProductExists(String productOfferId) {
        if (!productServiceClient.checkByProductOfferIdIfProductExists(productOfferId)) {
            throw new BusinessException(messageService.getMessage(Messages.BusinessErrors.NO_PRODUCT_FOUND));
        }
    }
}
