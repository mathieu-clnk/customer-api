package com.kamvity.samples.cm.service;

import com.kamvity.samples.cm.dto.Customer;
import com.kamvity.samples.cm.entity.CustomerEntity;
import com.kamvity.samples.cm.repository.CustomerJpaRepository;

import com.kamvity.samples.cm.response.Response;
import jakarta.persistence.EntityManager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {
    @Autowired
    private CustomerJpaRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Response<Customer>> createCustomer(Customer customer) {
        Response<Customer> response = new Response<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if(Objects.isNull(customerRepository.findByEmail(customer.getEmail()))) {
            try {
                response.setResult(convertToDto(customerRepository.save(convertFromDto(customer))));
                response.setStatus(response.SUCCESS);
                status = HttpStatus.CREATED;
            }catch (IllegalArgumentException ie) {
                response.setErrorMessage("Null object given.");
                response.setStatus(response.FAILED);
            }catch (OptimisticLockingFailureException op) {
                response.setErrorMessage("Error with the backend.");
                response.setStatus(response.FAILED);
            }

        }else {
            response.setStatus(response.FAILED);
            response.setErrorMessage("This email is already used.");
        }
        return ResponseEntity.status(status).body(response);
    }
    public ResponseEntity<Response<Customer>> findByEmail(String email) {

        CustomerEntity customerEntity = customerRepository.findByEmail(email);
        Response<Customer> response = new Response<>();
        HttpStatus status = HttpStatus.NOT_FOUND;
        if(Objects.isNull(customerEntity)) {
            response.setStatus(response.FAILED);
            response.setErrorMessage("CustomerEntity not found.");
        }else {
            Customer customer = convertToDto(customerEntity);
            response.setStatus(response.SUCCESS);
            response.setResult(customer);
            status = HttpStatus.OK;
        }
        return ResponseEntity.status(status).body(response);
    }

    private Customer convertToDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, Customer.class);
    }

    private CustomerEntity convertFromDto(Customer customer) { return modelMapper.map(customer, CustomerEntity.class);}
}
