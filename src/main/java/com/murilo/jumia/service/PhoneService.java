package com.murilo.jumia.service;

import com.murilo.jumia.dao.PhoneRepository;
import com.murilo.jumia.factory.PhoneFactory;
import com.murilo.jumia.model.*;
import com.murilo.jumia.model.DTO.PaginatedRepresentation;
import com.murilo.jumia.model.DTO.PhoneDTO;
import com.murilo.jumia.model.DTO.RequestFilter;
import com.murilo.jumia.model.entity.PhoneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final PhoneFactory phoneFactory;
    private static final Integer paginationSize = 10;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository, PhoneFactory phoneFactory) {
        this.phoneRepository = phoneRepository;
        this.phoneFactory = phoneFactory;
    }

    public PaginatedRepresentation<PhoneDTO> getPaginatedPhonesDTOByFilter(RequestFilter filter) {
        Page<PhoneEntity> resultList = getPaginatedPhonesByFilter(filter);

        List<PhoneDTO> responseData = new ArrayList<>();
        resultList.forEach(phoneEntity -> {
            BasicPhone phone = phoneFactory.getPhoneStrategy(phoneEntity.getNumber());
            responseData.add(new PhoneDTO(phone));
        });

        return PaginatedRepresentation.of(resultList.getTotalElements(), resultList.getTotalPages(), filter.getPage(), responseData);
    }

    private Page<PhoneEntity> getPaginatedPhonesByFilter(RequestFilter filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), paginationSize);

        if(filter.getState() != null){
            return phoneRepository.findByStateAndCountry(phoneFactory.getPhoneValidationRegex(), filter.getState(), filter.getCountryCode(), pageable);
        } else if (filter.getCountryCode() != null){
            return phoneRepository.findByPhoneNumberStartingWith(filter.getCountryCode(), pageable);
        } else{
            return phoneRepository.findAll(pageable);
        }
    }

}

