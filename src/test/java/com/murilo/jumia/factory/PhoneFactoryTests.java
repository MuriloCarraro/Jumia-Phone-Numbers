package com.murilo.jumia.factory;

import com.murilo.jumia.model.*;
import com.murilo.jumia.model.constants.CountryCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PhoneFactoryTests {


    private PhoneFactory factory = new PhoneFactory();

    private final int min = 10000000;
    private final int max = 999999999;

    @Test
    void getPhoneStrategyTest() {
        for (int i = 0; i < 50; i++) {
            Integer randomPhone = (int)(Math.random() * max + min);
            assertCameroonPhoneFromFactory("("+ CountryCode.CAMEROON+")" + randomPhone);
            assertCameroonPhoneFromFactory("("+ CountryCode.CAMEROON+") " + randomPhone);

            assertEthiopiaPhoneFromFactory("("+ CountryCode.ETHIOPIA+")" + randomPhone);
            assertEthiopiaPhoneFromFactory("("+ CountryCode.ETHIOPIA+") " + randomPhone);

            assertMoroccoPhoneFromFactory("("+ CountryCode.MOROCCO+")" + randomPhone);
            assertMoroccoPhoneFromFactory("("+ CountryCode.MOROCCO+") " + randomPhone);

            assertMozambiquePhoneFromFactory("("+ CountryCode.MOZAMBIQUE+")" + randomPhone);
            assertMozambiquePhoneFromFactory("("+ CountryCode.MOZAMBIQUE+") " + randomPhone);

            assertUgandaPhoneFromFactory("("+ CountryCode.UGANDA+")" + randomPhone);
            assertUgandaPhoneFromFactory("("+ CountryCode.UGANDA+") " + randomPhone);

            assertNotValidPhoneFromFactory( randomPhone + "321");
            assertNotValidPhoneFromFactory( randomPhone + "123");
        }
    }

    void assertCameroonPhoneFromFactory(String phone) {
        BasicPhone cameroonPhone = new CameroonPhone().setPhoneNumber(phone);
        BasicPhone phoneFactory = factory.getPhoneStrategy(phone);
        assertPhoneFromFactory(cameroonPhone, phoneFactory);
    }

    void assertEthiopiaPhoneFromFactory(String phone) {
        BasicPhone cameroonPhone = new EthiopiaPhone().setPhoneNumber(phone);
        BasicPhone phoneFactory = factory.getPhoneStrategy(phone);
        assertPhoneFromFactory(cameroonPhone, phoneFactory);
    }

    void assertMoroccoPhoneFromFactory(String phone) {
        BasicPhone cameroonPhone = new MoroccoPhone().setPhoneNumber(phone);
        BasicPhone phoneFactory = factory.getPhoneStrategy(phone);
        assertPhoneFromFactory(cameroonPhone, phoneFactory);
    }

    void assertMozambiquePhoneFromFactory(String phone) {
        BasicPhone cameroonPhone = new MozambiquePhone().setPhoneNumber(phone);
        BasicPhone phoneFactory = factory.getPhoneStrategy(phone);
        assertPhoneFromFactory(cameroonPhone, phoneFactory);
    }

    void assertUgandaPhoneFromFactory(String phone) {
        BasicPhone cameroonPhone = new UgandaPhone().setPhoneNumber(phone);
        BasicPhone phoneFactory = factory.getPhoneStrategy(phone);
        assertPhoneFromFactory(cameroonPhone, phoneFactory);
    }

    void assertNotValidPhoneFromFactory(String phone) {
        BasicPhone cameroonPhone = new NotValidPhone().setPhoneNumber(phone);
        BasicPhone phoneFactory = factory.getPhoneStrategy(phone);
        assertPhoneFromFactory(cameroonPhone, phoneFactory);
    }

    void assertPhoneFromFactory(BasicPhone basicPhone, BasicPhone phoneFactory ) {
        assertEquals(phoneFactory.getCountryCode(), basicPhone.getCountryCode());
        assertEquals(phoneFactory.getCountryName(), basicPhone.getCountryName());
        assertEquals(phoneFactory.getFormattedPhoneNumber(), basicPhone.getFormattedPhoneNumber());
        assertEquals(phoneFactory.isValid(), basicPhone.isValid());
    }

    @Test
    void getPhoneValidationRegexTest() {
        assertEquals(factory.getPhoneValidationRegex(), "\\(212\\)\\ ?[5-9]\\d{8}$|\\(256\\)\\ ?\\d{9}$|\\(258\\)\\ ?[28]\\d{7,8}$|\\(237\\)\\ ?[2368]\\d{7,8}$|\\(251\\)\\ ?[1-59]\\d{8}$");
    }
}
