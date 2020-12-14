package com.murilo.jumia.model;

import com.murilo.jumia.model.constants.CountryCode;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class MozambiquePhone extends PhoneWithValidation {

    private static final String countryCode = CountryCode.MOZAMBIQUE;
    private static final String countryName = "Mozambique";
    private static final String validationRegex = "\\("+countryCode+"\\)\\ ?[28]\\d{7,8}$";
    private static final Predicate<String> IS_PHONE_VALID = Pattern.compile(validationRegex,Pattern.CANON_EQ).asPredicate();

    @Override
    public boolean isValid(){
        return IS_PHONE_VALID.test(this.getPhoneNumber());
    }

    @Override
    public String getCountryCode() {
        return countryCode;
    }

    @Override
    public String getCountryName() {
        return countryName;
    }

    @Override
    public String getValidationRegex() {
        return validationRegex;
    }
}
