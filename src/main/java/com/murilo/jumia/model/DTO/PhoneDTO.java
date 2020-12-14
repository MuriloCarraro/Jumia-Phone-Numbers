package com.murilo.jumia.model.DTO;

import com.murilo.jumia.model.BasicPhone;

import javax.validation.constraints.NotNull;

public class PhoneDTO {

    @NotNull
    private final String phoneNumber;

    private final String countryCode;

    private final String countryName;

    @NotNull
    private final State state;

    public PhoneDTO(BasicPhone validPhone) {
        this.phoneNumber = validPhone.getFormattedPhoneNumber();
        this.countryCode = validPhone.getCountryCode();
        this.countryName = validPhone.getCountryName();
        this.state = validPhone.isValid() ? State.VALID : State.NOT_VALID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public String getCountryName() {
        return countryName;
    }
    public State getState() {
        return state;
    }

    public enum State {
        VALID, NOT_VALID
    }

}
