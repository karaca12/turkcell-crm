package com.turkcell.pair1.customerservice.service.validation.annotation;

import com.turkcell.pair1.customerservice.service.validation.validator.SearchMobilePhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SearchMobilePhoneValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchMobilePhone {
    String message() default "Invalid mobile phone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
