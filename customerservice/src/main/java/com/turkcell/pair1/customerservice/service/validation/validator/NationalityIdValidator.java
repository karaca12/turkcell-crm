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

        //basit algoritma(test etmek için)
        if (nationalityId == null || nationalityId.length() != 11) {
            return false;
        }
        return nationalityId.matches("\\d+");


        //gelişmiş algoritma(final product)
        /*if (nationalityId == null || nationalityId.length() != 11) {
            return false;
        }

        if (!nationalityId.matches("\\d+")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = Character.getNumericValue(nationalityId.charAt(i));
            sum += digit;
        }

        int remainder = sum % 10;

        int eleventhDigit = Character.getNumericValue(nationalityId.charAt(10));
        return remainder == eleventhDigit;*/
    }
}
