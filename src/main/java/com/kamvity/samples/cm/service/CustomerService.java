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
import java.util.Optional;

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
        Optional<CustomerEntity> customerEntity = customerRepository.findByEmail(customer.getEmail());
        customerEntity.ifPresentOrElse(
                (entity) -> {
                    response.setStatus(response.FAILED);
                    response.setErrorMessage("This email is already used.");
                },
                () -> {
                    try {
                        response.setResult(convertToDto(customerRepository.save(convertFromDto(customer))));
                        response.setStatus(response.SUCCESS);
                    }catch (IllegalArgumentException ie) {
                        response.setErrorMessage("Null object given.");
                        response.setStatus(response.FAILED);
                    }catch (OptimisticLockingFailureException op) {
                        response.setErrorMessage("Error with the backend.");
                        response.setStatus(response.FAILED);
                    }
                }
        );

        if(response.getStatus().equals(response.SUCCESS)) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    public ResponseEntity<Response<Customer>> findByEmail(String email) {

        Optional<CustomerEntity> customerEntity = customerRepository.findByEmail(email);
        Response<Customer> response = new Response<>();
        customerEntity.ifPresentOrElse(
                (entity) -> {
                    response.setStatus(response.SUCCESS);
                    response.setResult(convertToDto(entity));
                },
                () -> {
                    response.setStatus(response.FAILED);
                    response.setErrorMessage("Customer not found.");
                }
        );

        if(response.getStatus().equals(response.SUCCESS)) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<Response<Customer>> getById(Long id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        Response<Customer> response = new Response<>();
        customerEntity.ifPresentOrElse(
                (entity) -> {
                    response.setStatus(response.SUCCESS);
                    response.setResult(convertToDto(entity));
                },
                () -> {
                    response.setStatus(response.FAILED);
                    response.setErrorMessage("Customer not found.");
                }
        );

        if(response.getStatus().equals(response.SUCCESS)) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    private Customer convertToDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, Customer.class);
    }

    private CustomerEntity convertFromDto(Customer customer) { return modelMapper.map(customer, CustomerEntity.class);}
}
