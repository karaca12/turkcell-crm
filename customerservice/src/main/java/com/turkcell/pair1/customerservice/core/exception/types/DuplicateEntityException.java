package com.turkcell.pair1.customerservice.core.exception.types;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class DuplicateEntityException extends BusinessException {
    private final Map<String, String> errors = new HashMap<>();

    public DuplicateEntityException(String fieldName, String message) {
        super(message);
        this.errors.put(fieldName, message);
    }
}