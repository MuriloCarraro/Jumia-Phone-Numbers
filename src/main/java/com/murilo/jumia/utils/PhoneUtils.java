package com.murilo.jumia.utils;

public class PhoneUtils {

    public static String getCountryCode(String phone){
        if(phoneHasCountryCode(phone)){
            return phone.substring(1, 4);
        }
        return null;
    }

    public static boolean phoneHasCountryCode(String phone){
        return phone != null &&
                phone.length() > 4 &&
                Character.isDigit(phone.charAt(1)) &&
                Character.isDigit(phone.charAt(2)) &&
                Character.isDigit(phone.charAt(3)) &&
                "(".equals(String.valueOf(phone.charAt(0))) &&
                ")".equals(String.valueOf(phone.charAt(4)));
    }
}
