package com.turkcell.pair1.customerservice.service.validation.validator;

import com.turkcell.pair1.customerservice.service.validation.annotation.SearchMobilePhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchMobilePhoneValidator implements ConstraintValidator<SearchMobilePhone, String> {
    private static final String TURKISH_GSM_REGEX = "^905[0-9]{9}$";
    @Override
    public void initialize(SearchMobilePhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String mobilePhone, ConstraintValidatorContext constraintValidatorContext) {
        if (mobilePhone==null){
            return true;
        }

        if (mobilePhone.isEmpty()) {
            return false;
        }

        if (!mobilePhone.matches("\\d+")) {
            return false;
        }

        Pattern pattern = Pattern.compile(TURKISH_GSM_REGEX);
        Matcher matcher = pattern.matcher(mobilePhone);
        return matcher.matches();
    }
}
