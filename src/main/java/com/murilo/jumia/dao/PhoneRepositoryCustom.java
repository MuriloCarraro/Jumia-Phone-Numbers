package com.murilo.jumia.dao;

import com.murilo.jumia.model.DTO.PhoneDTO;
import com.murilo.jumia.model.entity.PhoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneRepositoryCustom {
    Page<PhoneEntity> findByStateAndCountry(String regex, PhoneDTO.State state, String countryCode, Pageable pageable);
}
