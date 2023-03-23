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

/**
 * Customer service
 * <pre>
 *     Service to create and retrieve customers.
 * </pre>
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerJpaRepository customerRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Create customer
     * @param customer: Customer DTO containing the required information.
     * @return a response of the operation with the created customer.
     */
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

    /**
     * Find a customer by its email address.
     * @param email: The unique email address of the customer.
     * @return a response of the operation with the customer found.
     */
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

    /**
     * Get customer by its identifier.
     * @param id: the unique identifier of the customer.
     * @return the response of the operation with the customer found.
     */
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

    /**
     * Convert a persisted customer entity to a customer Data Transfer Object.
     * @param customerEntity: the entity persisted into database.
     * @return the customer Data Transfer Object.
     */
    private Customer convertToDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, Customer.class);
    }

    /**
     * Convert a Data Transfer Object to a customer entity.
     * @param customer: the customer DTO.
     * @return the entity to persist in database.
     */
    private CustomerEntity convertFromDto(Customer customer) { return modelMapper.map(customer, CustomerEntity.class);}
}
