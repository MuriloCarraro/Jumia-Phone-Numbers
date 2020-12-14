package com.murilo.jumia.model;

import com.murilo.jumia.model.constants.CountryCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UgandaPhoneTests {

    private final int min = 100000000;
    private final int max = 999999999;

    @Test
    void validPhoneTest() {
        for (int i = 0; i < 50; i++) {
            Integer randomPhoneInt = (int)(Math.random() * max + min);
            String randomPhone = randomPhoneInt.toString();

            boolean isValid = randomPhone.length() == 9;

            BasicPhone phone = new UgandaPhone().setPhoneNumber("("+ CountryCode.UGANDA+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
            phone = new UgandaPhone().setPhoneNumber("("+ CountryCode.UGANDA+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
        }
    }
}
