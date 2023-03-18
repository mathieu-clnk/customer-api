package com.kamvity.samples.cm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamvity.samples.cm.config.CustomerConfig;
import com.kamvity.samples.cm.dto.Customer;

import com.kamvity.samples.cm.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = CustomerConfig.class)
public class CustomerControllerTest {
    @Value(value="${local.server.port}")
    private int port = 0;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testCreateCustomer() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:" + port+"/api/v1/customers/create";
        Customer customer = new Customer();
        customer.setEmail("frida.kahlo@email.org");
        customer.setLastname("Kahlo");
        customer.setTitle("Mrs");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(customer);
        HttpEntity<String> httpEntity = new HttpEntity<>(json,headers);
        Response<Customer> customerResponse = restTemplate.postForObject(url,httpEntity, Response.class);
        assertEquals("success",customerResponse.getStatus());
    }
    @Test
    @Sql("/delete-addresses.sql")
    @Sql("/delete-customer.sql")
    @Sql("/create-customer.sql")
    public void testGetCustomer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:" + port+"/api/v1/customers/get-by-email";
        HttpEntity<String> httpEntity = new HttpEntity<>("marie.curie@email.org",headers);
        Response<HashMap> customerResponse = restTemplate.postForObject(url,httpEntity, Response.class);
        assertEquals("marie.curie@email.org",customerResponse.getResult().get("email"));
    }

    @Test
    @Sql("/delete-addresses.sql")
    @Sql("/delete-customer.sql")
    @Sql("/create-customer.sql")
    public void testGetCustomerById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:" + port+"/api/v1/customers/get-by-id";
        HttpEntity<String> httpEntity = new HttpEntity<>("123456",headers);
        Response<HashMap> customerResponse = restTemplate.postForObject(url,httpEntity, Response.class);
        assertEquals("marie.curie@email.org",customerResponse.getResult().get("email"));
    }

}
