package com.murilo.jumia.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotValidPhoneTests {

    private final int min = 10000000;
    private final int max = 999999999;

    @Test
    void validPhoneTest() {
        for (int i = 0; i < 50; i++) {
            Integer randomPhoneInt = (int)(Math.random() * max + min);
            String randomPhone = randomPhoneInt.toString();

            BasicPhone phone = new NotValidPhone().setPhoneNumber(randomPhone);
            assertEquals(phone.isValid(), false);
            assertEquals(phone.getFormattedPhoneNumber(), randomPhone);
        }
    }
}
