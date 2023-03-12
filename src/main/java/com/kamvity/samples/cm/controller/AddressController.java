package com.kamvity.samples.cm.controller;

import com.kamvity.samples.cm.dto.Address;

import com.kamvity.samples.cm.response.Response;
import com.kamvity.samples.cm.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping(path = "/create",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<Address>> create(@RequestBody Address address) {
        return addressService.createAddress(address);
    }
}
