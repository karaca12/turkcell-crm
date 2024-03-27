package com.turkcell.pair1.customerservice.core.exception.details;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeignProblemDetails extends ProblemDetails{
    public FeignProblemDetails() {
        setTitle("Feign Rule Violation");
        setType("https://turkcell.com/exceptions/feign");
    }
}
