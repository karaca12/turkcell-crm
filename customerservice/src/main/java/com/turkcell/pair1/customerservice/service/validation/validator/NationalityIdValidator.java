package com.turkcell.pair1.customerservice.service.validation.validator;

import com.turkcell.pair1.customerservice.service.validation.annotation.NationalityId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NationalityIdValidator implements ConstraintValidator<NationalityId, Integer> {
    @Override
    public void initialize(NationalityId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer nationalityId, ConstraintValidatorContext constraintValidatorContext) {
        return nationalityId % 2 == 0;
    }
}
