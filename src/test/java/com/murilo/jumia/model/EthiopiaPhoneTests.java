package com.murilo.jumia.model;

import com.murilo.jumia.model.constants.CountryCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EthiopiaPhoneTests {

    private final int min = 100000000;
    private final int max = 999999999;

    @Test
    void validPhoneTest() {
        for (int i = 0; i < 50; i++) {
            Integer randomPhoneInt = (int)(Math.random() * max + min);
            String randomPhone = randomPhoneInt.toString();

            boolean isValid = (randomPhone.startsWith("1") ||
                    randomPhone.startsWith("2") ||
                    randomPhone.startsWith("3") ||
                    randomPhone.startsWith("4") ||
                    randomPhone.startsWith("5") ||
                    randomPhone.startsWith("9")) && randomPhone.length() == 9;

            BasicPhone phone = new EthiopiaPhone().setPhoneNumber("("+ CountryCode.ETHIOPIA+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
            phone = new EthiopiaPhone().setPhoneNumber("("+ CountryCode.ETHIOPIA+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
        }
    }
}
