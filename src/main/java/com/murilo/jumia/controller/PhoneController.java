package com.murilo.jumia.controller;

import com.murilo.jumia.model.DTO.PaginatedRepresentation;
import com.murilo.jumia.model.DTO.PhoneDTO;
import com.murilo.jumia.model.DTO.RequestFilter;
import com.murilo.jumia.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="api/phone")
public class PhoneController {

    private final PhoneService phoneService;

    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public ResponseEntity<PaginatedRepresentation<PhoneDTO>> getPhonesByFilter(@Valid RequestFilter filter) {
        return new ResponseEntity<>(phoneService.getPaginatedPhonesDTOByFilter(filter), new HttpHeaders(), HttpStatus.OK);
    }
}
