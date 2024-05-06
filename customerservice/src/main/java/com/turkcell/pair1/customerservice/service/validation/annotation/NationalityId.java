package com.turkcell.pair1.customerservice.service.validation.annotation;

import com.turkcell.pair1.customerservice.service.validation.validator.NationalityIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NationalityIdValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NationalityId {
    String message() default "Invalid nationality id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}