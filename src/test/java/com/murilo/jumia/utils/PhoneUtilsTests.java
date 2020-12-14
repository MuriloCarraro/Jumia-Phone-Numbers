package com.murilo.jumia.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PhoneUtilsTests {

    @Test
    void phoneHasCountryCodeTest() {
        assertEquals(PhoneUtils.phoneHasCountryCode("(251)612345678"), true);
        assertEquals(PhoneUtils.phoneHasCountryCode("(123)612345678"), true);
        assertEquals(PhoneUtils.phoneHasCountryCode("(123)"), true);
        assertEquals(PhoneUtils.phoneHasCountryCode("(321)6"), true);
        assertEquals(PhoneUtils.phoneHasCountryCode("(321) 6"), true);

        assertEquals(PhoneUtils.phoneHasCountryCode("( 251)612345678"), false);
        assertEquals(PhoneUtils.phoneHasCountryCode("(123 )612345678"), false);
        assertEquals(PhoneUtils.phoneHasCountryCode("(  )"), false);
        assertEquals(PhoneUtils.phoneHasCountryCode("(3 )6"), false);
        assertEquals(PhoneUtils.phoneHasCountryCode("( 1) 6"), false);
    }

    @Test
    void getCountryCodeTest() {
        assertEquals(PhoneUtils.getCountryCode("(251)612345678"), "251");
        assertEquals(PhoneUtils.getCountryCode("(123)612345678"), "123");
        assertEquals(PhoneUtils.getCountryCode("(123)"), "123");
        assertEquals(PhoneUtils.getCountryCode("(321)6"), "321");
        assertEquals(PhoneUtils.getCountryCode("(321) 6"), "321");

        assertEquals(PhoneUtils.getCountryCode("( 251)612345678"), null);
        assertEquals(PhoneUtils.getCountryCode("(123 )612345678"), null);
        assertEquals(PhoneUtils.getCountryCode("(  )"), null);
        assertEquals(PhoneUtils.getCountryCode("(3 )6"), null);
        assertEquals(PhoneUtils.getCountryCode("( 1) 6"), null);
    }
}
