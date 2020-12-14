package com.murilo.jumia.service;

import com.murilo.jumia.dao.PhoneRepository;
import com.murilo.jumia.factory.PhoneFactory;
import com.murilo.jumia.model.DTO.PaginatedRepresentation;
import com.murilo.jumia.model.DTO.PhoneDTO;
import com.murilo.jumia.model.DTO.RequestFilter;
import com.murilo.jumia.model.constants.CountryCode;
import com.murilo.jumia.model.entity.PhoneEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PhoneServiceTests {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneFactory phoneFactory;

    private PhoneService service;

    private  List<PhoneEntity> phones;

    @BeforeEach
    public void setUp() {
        service = new PhoneService(phoneRepository, phoneFactory);

        phones = new ArrayList<>();
        PhoneEntity phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(237)212345610");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(237)912345620");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(251)112345630");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(251)812345640");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(212)512345650");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(212)411234560");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(258)24567870");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(258)734567880");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(256)234567890");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("(256)3634567800");
        phones.add(phone);

        phone = new PhoneEntity();
        phone.setPhoneId(UUID.randomUUID());
        phone.setNumber("1233634567800");
        phones.add(phone);
    }

    @Test
    void getPaginatedPhonesTest() {

        RequestFilter filter = new RequestFilter();
        filter.setPage(0);
        Pageable pageable = PageRequest.of(filter.getPage(), 10);

        Page<PhoneEntity>  response = new PageImpl<>(phones, pageable, 11L);
        when(phoneRepository.findAll(pageable)).thenReturn(response);

        PaginatedRepresentation<PhoneDTO> serviceReturn = service.getPaginatedPhonesDTOByFilter(filter);
        assertEquals(serviceReturn.getPage(), pageable.getPageNumber());
        assertEquals(serviceReturn.getTotal(), response.getTotalElements());
        assertEquals(serviceReturn.getTotalPages(), response.getTotalPages());
        assertEquals(serviceReturn.getData().size(), 11);

        assertEquals(serviceReturn.getData().get(0).getPhoneNumber(), "212345610");
        assertEquals(serviceReturn.getData().get(0).getCountryCode(), CountryCode.CAMEROON);
        assertEquals(serviceReturn.getData().get(0).getCountryName(), "Cameroon");
        assertEquals(serviceReturn.getData().get(0).getState(), PhoneDTO.State.VALID);

        assertEquals(serviceReturn.getData().get(1).getPhoneNumber(), "912345620");
        assertEquals(serviceReturn.getData().get(1).getCountryCode(), CountryCode.CAMEROON);
        assertEquals(serviceReturn.getData().get(1).getCountryName(), "Cameroon");
        assertEquals(serviceReturn.getData().get(1).getState(), PhoneDTO.State.NOT_VALID);

        assertEquals(serviceReturn.getData().get(2).getPhoneNumber(), "112345630");
        assertEquals(serviceReturn.getData().get(2).getCountryCode(), CountryCode.ETHIOPIA);
        assertEquals(serviceReturn.getData().get(2).getCountryName(), "Ethiopia");
        assertEquals(serviceReturn.getData().get(2).getState(), PhoneDTO.State.VALID);

        assertEquals(serviceReturn.getData().get(3).getPhoneNumber(), "812345640");
        assertEquals(serviceReturn.getData().get(3).getCountryCode(), CountryCode.ETHIOPIA);
        assertEquals(serviceReturn.getData().get(3).getCountryName(), "Ethiopia");
        assertEquals(serviceReturn.getData().get(3).getState(), PhoneDTO.State.NOT_VALID);

        assertEquals(serviceReturn.getData().get(4).getPhoneNumber(), "512345650");
        assertEquals(serviceReturn.getData().get(4).getCountryCode(), CountryCode.MOROCCO);
        assertEquals(serviceReturn.getData().get(4).getCountryName(), "Morocco");
        assertEquals(serviceReturn.getData().get(4).getState(), PhoneDTO.State.VALID);

        assertEquals(serviceReturn.getData().get(5).getPhoneNumber(), "411234560");
        assertEquals(serviceReturn.getData().get(5).getCountryCode(), CountryCode.MOROCCO);
        assertEquals(serviceReturn.getData().get(5).getCountryName(), "Morocco");
        assertEquals(serviceReturn.getData().get(5).getState(), PhoneDTO.State.NOT_VALID);

        assertEquals(serviceReturn.getData().get(6).getPhoneNumber(), "24567870");
        assertEquals(serviceReturn.getData().get(6).getCountryCode(), CountryCode.MOZAMBIQUE);
        assertEquals(serviceReturn.getData().get(6).getCountryName(), "Mozambique");
        assertEquals(serviceReturn.getData().get(6).getState(), PhoneDTO.State.VALID);

        assertEquals(serviceReturn.getData().get(7).getPhoneNumber(), "734567880");
        assertEquals(serviceReturn.getData().get(7).getCountryCode(), CountryCode.MOZAMBIQUE);
        assertEquals(serviceReturn.getData().get(7).getCountryName(), "Mozambique");
        assertEquals(serviceReturn.getData().get(7).getState(), PhoneDTO.State.NOT_VALID);

        assertEquals(serviceReturn.getData().get(8).getPhoneNumber(), "234567890");
        assertEquals(serviceReturn.getData().get(8).getCountryCode(), CountryCode.UGANDA);
        assertEquals(serviceReturn.getData().get(8).getCountryName(), "Uganda");
        assertEquals(serviceReturn.getData().get(8).getState(), PhoneDTO.State.VALID);

        assertEquals(serviceReturn.getData().get(9).getPhoneNumber(), "3634567800");
        assertEquals(serviceReturn.getData().get(9).getCountryCode(), CountryCode.UGANDA);
        assertEquals(serviceReturn.getData().get(9).getCountryName(), "Uganda");
        assertEquals(serviceReturn.getData().get(9).getState(), PhoneDTO.State.NOT_VALID);

        assertEquals(serviceReturn.getData().get(10).getPhoneNumber(), "1233634567800");
        assertEquals(serviceReturn.getData().get(10).getCountryCode(), "-");
        assertEquals(serviceReturn.getData().get(10).getCountryName(), "-");
        assertEquals(serviceReturn.getData().get(10).getState(), PhoneDTO.State.NOT_VALID);
    }

    @Test
    void getPaginatedPhonesByFilterCountryCodeTest() {

        RequestFilter filter = new RequestFilter();
        filter.setPage(0);
        filter.setCountryCode(CountryCode.CAMEROON);

        Pageable pageable = PageRequest.of(filter.getPage(), 10);

        Page<PhoneEntity>  response = new PageImpl<>(phones.stream().filter(e -> e.getNumber().startsWith("(237)")).collect(Collectors.toList()), pageable, 11L);
        when(phoneRepository.findByPhoneNumberStartingWith(filter.getCountryCode(), pageable)).thenReturn(response);

        PaginatedRepresentation<PhoneDTO> serviceReturn = service.getPaginatedPhonesDTOByFilter(filter);
        assertEquals(serviceReturn.getData().size(), 2);
        assertEquals(serviceReturn.getPage(), pageable.getPageNumber());
        assertEquals(serviceReturn.getTotal(), response.getTotalElements());
        assertEquals(serviceReturn.getTotalPages(), response.getTotalPages());

        assertEquals(serviceReturn.getData().get(0).getPhoneNumber(), "212345610");
        assertEquals(serviceReturn.getData().get(0).getCountryCode(), CountryCode.CAMEROON);
        assertEquals(serviceReturn.getData().get(0).getCountryName(), "Cameroon");
        assertEquals(serviceReturn.getData().get(0).getState(), PhoneDTO.State.VALID);

        assertEquals(serviceReturn.getData().get(1).getPhoneNumber(), "912345620");
        assertEquals(serviceReturn.getData().get(1).getCountryCode(), CountryCode.CAMEROON);
        assertEquals(serviceReturn.getData().get(1).getCountryName(), "Cameroon");
        assertEquals(serviceReturn.getData().get(1).getState(), PhoneDTO.State.NOT_VALID);
    }

    @Test
    void getPaginatedPhonesByFilterStateTest() {

        RequestFilter filter = new RequestFilter();
        filter.setPage(0);
        filter.setState(PhoneDTO.State.VALID);
        filter.setCountryCode(CountryCode.CAMEROON);

        Pageable pageable = PageRequest.of(filter.getPage(), 10);

        Page<PhoneEntity>  response = new PageImpl<>(phones.stream().filter(e -> e.getNumber().startsWith("(237)")).collect(Collectors.toList()), pageable, 11L);
        when(phoneRepository.findByStateAndCountry(phoneFactory.getPhoneValidationRegex(), filter.getState(), filter.getCountryCode(), pageable)).thenReturn(response);

        PaginatedRepresentation<PhoneDTO> serviceReturn = service.getPaginatedPhonesDTOByFilter(filter);
        assertEquals(serviceReturn.getData().size(), 2);
        assertEquals(serviceReturn.getPage(), pageable.getPageNumber());
        assertEquals(serviceReturn.getTotal(), response.getTotalElements());
        assertEquals(serviceReturn.getTotalPages(), response.getTotalPages());

        assertEquals(serviceReturn.getData().get(0).getPhoneNumber(), "212345610");
        assertEquals(serviceReturn.getData().get(0).getCountryCode(), CountryCode.CAMEROON);
        assertEquals(serviceReturn.getData().get(0).getCountryName(), "Cameroon");
        assertEquals(serviceReturn.getData().get(0).getState(), PhoneDTO.State.VALID);

        assertEquals(serviceReturn.getData().get(1).getPhoneNumber(), "912345620");
        assertEquals(serviceReturn.getData().get(1).getCountryCode(), CountryCode.CAMEROON);
        assertEquals(serviceReturn.getData().get(1).getCountryName(), "Cameroon");
        assertEquals(serviceReturn.getData().get(1).getState(), PhoneDTO.State.NOT_VALID);
    }

}