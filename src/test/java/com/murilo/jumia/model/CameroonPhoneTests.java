package com.murilo.jumia.model;

import com.murilo.jumia.model.constants.CountryCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CameroonPhoneTests {

    private final int min = 10000000;
    private final int max = 999999999;

    @Test
    void validPhoneTest() {
        for (int i = 0; i < 50; i++) {
            Integer randomPhoneInt = (int)(Math.random() * max + min);
            String randomPhone = randomPhoneInt.toString();

            boolean isValid = randomPhone.startsWith("2") || randomPhone.startsWith("3") || randomPhone.startsWith("6") || randomPhone.startsWith("8");
            BasicPhone phone = new CameroonPhone().setPhoneNumber("("+ CountryCode.CAMEROON+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
            phone = new CameroonPhone().setPhoneNumber("("+ CountryCode.CAMEROON+")" + randomPhone);
            assertEquals(phone.isValid(), isValid);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
        }
    }
}
