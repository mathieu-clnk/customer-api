package com.kamvity.samples.cm.service;

import com.kamvity.samples.cm.config.CustomerConfig;

import com.kamvity.samples.cm.dto.Customer;
import com.kamvity.samples.cm.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CustomerConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Value(value="${local.server.port}")
    private int port = 0;


    @Test
    public void testCreateCustomerEssential() {
        Customer customer = new Customer();
        customer.setEmail("amelia.earhart@email.org");
        customer.setLastname("Earhart");
        customer.setTitle("Mrs");
        ResponseEntity<Response<Customer>> responseEntity = customerService.createCustomer(customer);
        assertEquals("success",responseEntity.getBody().getStatus());
        Customer resultCustomer = responseEntity.getBody().getResult();
        assertEquals("amelia.earhart@email.org",resultCustomer.getEmail());
        assert resultCustomer.getId() > 0;
    }

    @Test
    @Sql("/delete-addresses.sql")
    @Sql("/delete-customer.sql")
    @Sql("/create-customer.sql")
    public void testCreateExistingCustomer() {
        Customer customer = new Customer();
        customer.setEmail("marie.curie@email.org");
        customer.setLastname("Curie");
        customer.setTitle("Mrs");
        ResponseEntity<Response<Customer>> responseEntity = customerService.createCustomer(customer);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
        assertEquals("failed",responseEntity.getBody().getStatus());
        assertEquals("This email is already used.",responseEntity.getBody().getErrorMessage());
    }

}
