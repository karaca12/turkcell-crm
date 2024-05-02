package com.turkcell.pair1.customerservice.service.validation.annotation;

import com.turkcell.pair1.customerservice.service.validation.validator.CreateMobilePhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CreateMobilePhoneValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateMobilePhone {
    String message() default "Invalid mobile phone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
