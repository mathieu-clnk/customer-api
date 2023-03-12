package com.kamvity.samples.cm.service;

import com.kamvity.samples.cm.dto.Address;
import com.kamvity.samples.cm.entity.AddressEntity;
import com.kamvity.samples.cm.repository.AddressJpaRepository;
import com.kamvity.samples.cm.response.Response;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressJpaRepository addressRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Response<Address>> createAddress(Address address) {
        Response<Address> response = new Response<Address>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        try {
            response.setResult(convertToDto(addressRepository.save(convertFromDto(address))));
            response.setStatus(response.SUCCESS);
            status = HttpStatus.CREATED;
        }catch (IllegalArgumentException ie) {
            response.setErrorMessage("Null object given.");
            response.setStatus(response.SUCCESS);
        }catch (OptimisticLockingFailureException op) {
            response.setErrorMessage("Error with the backend.");
            response.setStatus(response.SUCCESS);
        }
        return ResponseEntity.status(status).body(response);
    }

    private Address convertToDto(AddressEntity addressEntity) {
        return modelMapper.map(addressEntity, Address.class);
    }

    private AddressEntity convertFromDto(Address address) {
        return modelMapper.map(address, AddressEntity.class);
    }
}
