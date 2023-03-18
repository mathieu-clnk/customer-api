package com.kamvity.samples.cm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamvity.samples.cm.config.CustomerConfig;
import com.kamvity.samples.cm.dto.Address;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = CustomerConfig.class)
public class AddressControllerTest {

    @Value(value="${local.server.port}")
    private int port = 0;

    @Autowired
    private TestRestTemplate restTemplate;

    public Address createAddress() {
        Address address = new Address();
        address.setAddressId(Long.decode("1234"));
        address.setAddress("3 rue Henri Janin");
        address.setName("Home");
        address.setCity("Saint-Rémy-lès-Chevreuse");
        address.setCountry("France");
        address.setZipCode("78575");
        address.setCustomerId(Long.decode("123456"));
        address.setIsDefault(Boolean.TRUE);

        return address;
    }

    @Test
    @Sql("/delete-customer.sql")
    @Sql("/create-customer.sql")
    public void testCreateAddress() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String url = "http://localhost:" + port+"/api/v1/address/create";
        Address address = createAddress();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(address);
        HttpEntity<String> httpEntity = new HttpEntity<>(json,headers);
        Response<Address> addressResponse = restTemplate.postForObject(url,httpEntity, Response.class);
        assertEquals("success",addressResponse.getStatus());
    }
}
