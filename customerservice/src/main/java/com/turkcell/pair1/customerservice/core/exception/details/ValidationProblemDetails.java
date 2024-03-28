package com.turkcell.pair1.customerservice.core.exception.details;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationProblemDetails {
    private String title;
    private Map<String, String> detail;
    private String type;

    public ValidationProblemDetails() {
        setTitle("Validation Exception");
        setType("https://turkcell.com/exceptions/validation");
    }
}
