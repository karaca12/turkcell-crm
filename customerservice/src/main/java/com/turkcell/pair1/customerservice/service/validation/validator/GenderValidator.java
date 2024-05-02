package com.turkcell.pair1.customerservice.service.validation.validator;

import com.turkcell.pair1.customerservice.service.validation.annotation.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {
    @Override
    public void initialize(Gender constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {
        return gender != null && (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"));
    }
}
