package com.kamvity.samples.cm.controller;

import com.kamvity.samples.cm.dto.Customer;
import com.kamvity.samples.cm.response.Response;
import com.kamvity.samples.cm.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "customers", description = "Customer API")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/create",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "createCustomer",description = "Create a new customer")
    @Parameter(name = "customer", schema = @Schema(implementation = Customer.class))
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PostMapping(path = "/get-by-email",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Customer>> getByEmail(@RequestBody String email) {
        return customerService.findByEmail(email);
    }
    @PostMapping(path = "/get-by-id",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Customer>> getById(@RequestBody String id) {
        return customerService.getById(Long.decode(id));
    }

}
