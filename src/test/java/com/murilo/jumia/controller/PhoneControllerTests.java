package com.murilo.jumia.controller;


import com.murilo.jumia.model.*;
import com.murilo.jumia.model.DTO.PaginatedRepresentation;
import com.murilo.jumia.model.DTO.PhoneDTO;
import com.murilo.jumia.model.DTO.RequestFilter;
import com.murilo.jumia.service.PhoneService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PhoneController.class)
public class PhoneControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneService service;

    @Test
    public void getCameroonPhoneTest() throws Exception {
        List<PhoneDTO> responseData = new ArrayList<>();

        CameroonPhone phoneOne = new CameroonPhone();
        phoneOne.setPhoneNumber("(237)212345675");
        responseData.add(new PhoneDTO(phoneOne));

        CameroonPhone phoneTwo = new CameroonPhone();
        phoneTwo.setPhoneNumber("(237) 232345675");
        responseData.add(new PhoneDTO(phoneTwo));

        CameroonPhone phoneThree = new CameroonPhone();
        phoneThree.setPhoneNumber("(237)91234563");
        responseData.add(new PhoneDTO(phoneThree));

        PaginatedRepresentation<PhoneDTO> response = PaginatedRepresentation.of(20L, 2, 1, responseData);
        when(service.getPaginatedPhonesDTOByFilter(Mockito.any(RequestFilter.class))).thenReturn(response);

        this.mockMvc.perform(get("/api/phone"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(response.getTotal().intValue())))
                .andExpect(jsonPath("$.totalPages", is(response.getTotalPages())))
                .andExpect(jsonPath("$.page", is(response.getPage())))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].phoneNumber", is("212345675")))
                .andExpect(jsonPath("$.data[0].countryCode", is("237")))
                .andExpect(jsonPath("$.data[0].countryName", is("Cameroon")))
                .andExpect(jsonPath("$.data[0].state", is("VALID")))
                .andExpect(jsonPath("$.data[1].phoneNumber", is("232345675")))
                .andExpect(jsonPath("$.data[1].countryCode", is("237")))
                .andExpect(jsonPath("$.data[1].countryName", is("Cameroon")))
                .andExpect(jsonPath("$.data[1].state", is("VALID")))
                .andExpect(jsonPath("$.data[2].phoneNumber", is("91234563")))
                .andExpect(jsonPath("$.data[2].countryCode", is("237")))
                .andExpect(jsonPath("$.data[2].countryName", is("Cameroon")))
                .andExpect(jsonPath("$.data[2].state", is("NOT_VALID")));
    }

    @Test
    public void getEthiopiaPhoneTest() throws Exception {
        List<PhoneDTO> responseData = new ArrayList<>();

        EthiopiaPhone phoneOne = new EthiopiaPhone();
        phoneOne.setPhoneNumber("(251)912345678");
        responseData.add(new PhoneDTO(phoneOne));

        EthiopiaPhone phoneTwo = new EthiopiaPhone();
        phoneTwo.setPhoneNumber("(251) 932345678");
        responseData.add(new PhoneDTO(phoneTwo));

        EthiopiaPhone phoneThree = new EthiopiaPhone();
        phoneThree.setPhoneNumber("(251)12345678");
        responseData.add(new PhoneDTO(phoneThree));

        PaginatedRepresentation<PhoneDTO> response = PaginatedRepresentation.of(20L, 2, 1, responseData);
        when(service.getPaginatedPhonesDTOByFilter(Mockito.any(RequestFilter.class))).thenReturn(response);

        this.mockMvc.perform(get("/api/phone"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(response.getTotal().intValue())))
                .andExpect(jsonPath("$.totalPages", is(response.getTotalPages())))
                .andExpect(jsonPath("$.page", is(response.getPage())))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].phoneNumber", is("912345678")))
                .andExpect(jsonPath("$.data[0].countryCode", is("251")))
                .andExpect(jsonPath("$.data[0].countryName", is("Ethiopia")))
                .andExpect(jsonPath("$.data[0].state", is("VALID")))
                .andExpect(jsonPath("$.data[1].phoneNumber", is("932345678")))
                .andExpect(jsonPath("$.data[1].countryCode", is("251")))
                .andExpect(jsonPath("$.data[1].countryName", is("Ethiopia")))
                .andExpect(jsonPath("$.data[1].state", is("VALID")))
                .andExpect(jsonPath("$.data[2].phoneNumber", is("12345678")))
                .andExpect(jsonPath("$.data[2].countryCode", is("251")))
                .andExpect(jsonPath("$.data[2].countryName", is("Ethiopia")))
                .andExpect(jsonPath("$.data[2].state", is("NOT_VALID")));
    }

    @Test
    public void getMoroccoPhoneTest() throws Exception {
        List<PhoneDTO> responseData = new ArrayList<>();

        MoroccoPhone phoneOne = new MoroccoPhone();
        phoneOne.setPhoneNumber("(212)512345678");
        responseData.add(new PhoneDTO(phoneOne));

        MoroccoPhone phoneTwo = new MoroccoPhone();
        phoneTwo.setPhoneNumber("(212) 512345690");
        responseData.add(new PhoneDTO(phoneTwo));

        MoroccoPhone phoneThree = new MoroccoPhone();
        phoneThree.setPhoneNumber("(212)411234567");
        responseData.add(new PhoneDTO(phoneThree));

        PaginatedRepresentation<PhoneDTO> response = PaginatedRepresentation.of(20L, 2, 1, responseData);
        when(service.getPaginatedPhonesDTOByFilter(Mockito.any(RequestFilter.class))).thenReturn(response);

        this.mockMvc.perform(get("/api/phone"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(response.getTotal().intValue())))
                .andExpect(jsonPath("$.totalPages", is(response.getTotalPages())))
                .andExpect(jsonPath("$.page", is(response.getPage())))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].phoneNumber", is("512345678")))
                .andExpect(jsonPath("$.data[0].countryCode", is("212")))
                .andExpect(jsonPath("$.data[0].countryName", is("Morocco")))
                .andExpect(jsonPath("$.data[0].state", is("VALID")))
                .andExpect(jsonPath("$.data[1].phoneNumber", is("512345690")))
                .andExpect(jsonPath("$.data[1].countryCode", is("212")))
                .andExpect(jsonPath("$.data[1].countryName", is("Morocco")))
                .andExpect(jsonPath("$.data[1].state", is("VALID")))
                .andExpect(jsonPath("$.data[2].phoneNumber", is("411234567")))
                .andExpect(jsonPath("$.data[2].countryCode", is("212")))
                .andExpect(jsonPath("$.data[2].countryName", is("Morocco")))
                .andExpect(jsonPath("$.data[2].state", is("NOT_VALID")));
    }

    @Test
    public void getMozambiquePhoneTest() throws Exception {
        List<PhoneDTO> responseData = new ArrayList<>();

        MozambiquePhone phoneOne = new MozambiquePhone();
        phoneOne.setPhoneNumber("(258)214567863");
        responseData.add(new PhoneDTO(phoneOne));

        MozambiquePhone phoneTwo = new MozambiquePhone();
        phoneTwo.setPhoneNumber("(258) 214567890");
        responseData.add(new PhoneDTO(phoneTwo));

        MozambiquePhone phoneThree = new MozambiquePhone();
        phoneThree.setPhoneNumber("(258)734567863");
        responseData.add(new PhoneDTO(phoneThree));

        PaginatedRepresentation<PhoneDTO> response = PaginatedRepresentation.of(20L, 2, 1, responseData);
        when(service.getPaginatedPhonesDTOByFilter(Mockito.any(RequestFilter.class))).thenReturn(response);

        this.mockMvc.perform(get("/api/phone"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(response.getTotal().intValue())))
                .andExpect(jsonPath("$.totalPages", is(response.getTotalPages())))
                .andExpect(jsonPath("$.page", is(response.getPage())))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].phoneNumber", is("214567863")))
                .andExpect(jsonPath("$.data[0].countryCode", is("258")))
                .andExpect(jsonPath("$.data[0].countryName", is("Mozambique")))
                .andExpect(jsonPath("$.data[0].state", is("VALID")))
                .andExpect(jsonPath("$.data[1].phoneNumber", is("214567890")))
                .andExpect(jsonPath("$.data[1].countryCode", is("258")))
                .andExpect(jsonPath("$.data[1].countryName", is("Mozambique")))
                .andExpect(jsonPath("$.data[1].state", is("VALID")))
                .andExpect(jsonPath("$.data[2].phoneNumber", is("734567863")))
                .andExpect(jsonPath("$.data[2].countryCode", is("258")))
                .andExpect(jsonPath("$.data[2].countryName", is("Mozambique")))
                .andExpect(jsonPath("$.data[2].state", is("NOT_VALID")));
    }

    @Test
    public void getUgandaPhoneTest() throws Exception {
        List<PhoneDTO> responseData = new ArrayList<>();

        UgandaPhone phoneOne = new UgandaPhone();
        phoneOne.setPhoneNumber("(256)134567863");
        responseData.add(new PhoneDTO(phoneOne));

        UgandaPhone phoneTwo = new UgandaPhone();
        phoneTwo.setPhoneNumber("(256) 134567890");
        responseData.add(new PhoneDTO(phoneTwo));

        UgandaPhone phoneThree = new UgandaPhone();
        phoneThree.setPhoneNumber("(256)5634567863");
        responseData.add(new PhoneDTO(phoneThree));

        PaginatedRepresentation<PhoneDTO> response = PaginatedRepresentation.of(20L, 2, 1, responseData);
        when(service.getPaginatedPhonesDTOByFilter(Mockito.any(RequestFilter.class))).thenReturn(response);

        this.mockMvc.perform(get("/api/phone"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(response.getTotal().intValue())))
                .andExpect(jsonPath("$.totalPages", is(response.getTotalPages())))
                .andExpect(jsonPath("$.page", is(response.getPage())))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].phoneNumber", is("134567863")))
                .andExpect(jsonPath("$.data[0].countryCode", is("256")))
                .andExpect(jsonPath("$.data[0].countryName", is("Uganda")))
                .andExpect(jsonPath("$.data[0].state", is("VALID")))
                .andExpect(jsonPath("$.data[1].phoneNumber", is("134567890")))
                .andExpect(jsonPath("$.data[1].countryCode", is("256")))
                .andExpect(jsonPath("$.data[1].countryName", is("Uganda")))
                .andExpect(jsonPath("$.data[1].state", is("VALID")))
                .andExpect(jsonPath("$.data[2].phoneNumber", is("5634567863")))
                .andExpect(jsonPath("$.data[2].countryCode", is("256")))
                .andExpect(jsonPath("$.data[2].countryName", is("Uganda")))
                .andExpect(jsonPath("$.data[2].state", is("NOT_VALID")));
    }

}