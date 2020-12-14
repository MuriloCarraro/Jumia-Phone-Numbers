package com.murilo.jumia.model;

import com.murilo.jumia.model.constants.CountryCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoroccoPhoneTests {

    private final int min = 100000000;
    private final int max = 999999999;

    @Test
    void validPhoneTest() {
        for (int i = 0; i < 50; i++) {
            Integer randomPhoneInt = (int)(Math.random() * max + min);
            String randomPhone = randomPhoneInt.toString();

            boolean isValid = (randomPhone.startsWith("5") ||
                    randomPhone.startsWith("6") ||
                    randomPhone.startsWith("7") ||
                    randomPhone.startsWith("8") ||
                    randomPhone.startsWith("9")) && randomPhone.length() == 9;

            BasicPhone phone = new MoroccoPhone().setPhoneNumber("("+ CountryCode.MOROCCO+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
            phone = new MoroccoPhone().setPhoneNumber("("+ CountryCode.MOROCCO+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
        }
    }
}
