package com.kamvity.samples.cm.service;

import com.kamvity.samples.cm.config.CustomerConfig;
import com.kamvity.samples.cm.dto.Address;
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
public class AddressServiceTest {

    @Autowired
    AddressService addressService;

    @Value(value="${local.server.port}")
    private int port = 0;

    public Address createAddress() {
        Address address = new Address();
        address.setAddressId(Long.decode("1"));
        address.setAddress("12 rue du lac");
        address.setName("Home");
        address.setCity("Lyon");
        address.setCountry("France");
        address.setZipCode("69003");
        address.setCustomerId(Long.decode("123456"));
        address.setIsDefault(Boolean.TRUE);

        return address;
    }

    @Test
    @Sql("/delete-addresses.sql")
    @Sql("/delete-customer.sql")
    @Sql("/create-customer.sql")
    public void testCreateAddress() {
        Address address = createAddress();
        ResponseEntity<Response<Address>> responseEntity = addressService.createAddress(address);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals("Lyon",responseEntity.getBody().getResult().getCity());
    }
}
