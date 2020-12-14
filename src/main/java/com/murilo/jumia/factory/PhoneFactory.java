package com.murilo.jumia.factory;

import com.murilo.jumia.utils.PhoneUtils;
import com.murilo.jumia.model.*;
import com.murilo.jumia.model.constants.CountryCode;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PhoneFactory {

    private static final Map<String, PhoneWithValidation> strategies = new HashMap<>();

    static {
        strategies.put(CountryCode.CAMEROON, new CameroonPhone());
        strategies.put(CountryCode.ETHIOPIA, new EthiopiaPhone());
        strategies.put(CountryCode.MOROCCO, new MoroccoPhone());
        strategies.put(CountryCode.MOZAMBIQUE, new MozambiquePhone());
        strategies.put(CountryCode.UGANDA, new UgandaPhone());
    }

    public BasicPhone getPhoneStrategy(String phone) {
        String countryCode = PhoneUtils.getCountryCode(phone);
        if (!strategies.containsKey(countryCode)) {
            return new NotValidPhone().setPhoneNumber(phone);
        }
        return strategies.get(countryCode).setPhoneNumber(phone);
    }

    public String getPhoneValidationRegex() {
        return strategies.values().stream().map(PhoneWithValidation::getValidationRegex).collect(Collectors.joining("|"));
    }
}
