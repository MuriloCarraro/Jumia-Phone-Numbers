package com.murilo.jumia.model;

import com.murilo.jumia.utils.PhoneUtils;

public abstract class BasicPhone {
    private static String phone;

    String getPhoneNumber(){
        return phone;
    }

    public BasicPhone setPhoneNumber(String newPhone){
        phone = newPhone;
        return this;
    }

    public String getFormattedPhoneNumber(){
        if(PhoneUtils.phoneHasCountryCode(phone)){
            return phone.substring(5).trim();
        }
        return phone;
    }

    public abstract String getCountryCode();
    public abstract String getCountryName();
    public abstract boolean isValid();
}
