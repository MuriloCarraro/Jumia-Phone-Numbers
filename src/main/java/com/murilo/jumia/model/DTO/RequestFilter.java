package com.murilo.jumia.model.DTO;

import org.springframework.beans.factory.annotation.Value;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class RequestFilter {

    public RequestFilter() {
    }

    @Pattern(regexp="\\d{3}$" , flags = Pattern.Flag.UNICODE_CASE)
    private String countryCode;

    private PhoneDTO.State state;

    @Min(0)
    @Value("${page:0}")
    private int page;


    public String getCountryCode(){
        return this.countryCode;
    }
    public PhoneDTO.State getState(){
        return this.state;
    }
    public int getPage(){
        return this.page;
    }

    public void setCountryCode(String countryCode){
        this.countryCode = "(" + countryCode + ")";
    }
    public void setState(PhoneDTO.State state){
        this.state = state;
    }
    public void setPage(int page){
        this.page = page;
    }
}
