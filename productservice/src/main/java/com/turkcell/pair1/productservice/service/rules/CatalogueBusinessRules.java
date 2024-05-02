package com.turkcell.pair1.productservice.service.rules;

import com.turkcell.common.message.Messages;
import com.turkcell.pair1.configuration.exception.types.BusinessException;
import com.turkcell.pair1.productservice.entity.Catalogue;
import com.turkcell.pair1.productservice.repository.CatalogueRepository;
import com.turkcell.pair1.service.abstraction.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatalogueBusinessRules {
    private final CatalogueRepository catalogueRepository;
    private final MessageService messageService;

    public Catalogue findByIsDeletedFalseAndCatalogueId(Integer catalogueId) {
        return catalogueRepository.findByIsDeletedFalseAndId(catalogueId).orElseThrow(
                () -> new BusinessException("NO_CATALOGUE_FOUND")); //TODO: add message to common
    }
}
