package com.murilo.jumia.dao;

import com.murilo.jumia.model.entity.PhoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhoneRepository extends PagingAndSortingRepository<PhoneEntity, Integer>, PhoneRepositoryCustom {
    Page<PhoneEntity> findByPhoneNumberStartingWith(String countryCode, Pageable pageable);
}

