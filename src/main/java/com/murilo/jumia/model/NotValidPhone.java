package com.murilo.jumia.model;

public class NotValidPhone extends BasicPhone {
    private static final String defaultString = "-";

    public String getCountryCode(){
        return defaultString;
    }
    public String getCountryName(){
        return defaultString;
    }
    public boolean isValid(){
        return false;
    }
}
