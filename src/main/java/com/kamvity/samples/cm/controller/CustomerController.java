package com.kamvity.samples.cm.controller;

import com.kamvity.samples.cm.dto.Customer;
import com.kamvity.samples.cm.response.Response;
import com.kamvity.samples.cm.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/create",consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PostMapping(path = "/get-by-email",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Customer>> getByEmail(@RequestBody String email) {
        ResponseEntity<Response<Customer>> response = customerService.findByEmail(email);
        return response;
    }

}
