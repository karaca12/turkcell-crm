package com.turkcell.pair1.customerservice.service.validation.validator;

import com.turkcell.pair1.customerservice.service.validation.annotation.NationalityId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NationalityIdValidator implements ConstraintValidator<NationalityId, String> {
    @Override
    public void initialize(NationalityId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String nationalityId, ConstraintValidatorContext constraintValidatorContext) {
        if (nationalityId != null) {
            return nationalityId.length() == 11;
        } else {
            return false;
        }

    }
}
