package com.kamvity.samples.cm.repository;

import com.kamvity.samples.cm.entity.AddressEntity;
import com.kamvity.samples.cm.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AddressJpaRepositoryTest {

    @Autowired
    AddressJpaRepository addressJpaRepository;

    @Test
    @Sql("/delete-addresses.sql")
    @Sql("/delete-customer.sql")
    @Sql("/create-customer.sql")
    @Sql("/create-addresses.sql")
    public void testGetAddressesByCustomer() {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(Long.decode("123456"));
        customerEntity.setEmail("marie.curie@email.org");
        List<AddressEntity> addressEntities = addressJpaRepository.getAddressesByCustomer(customerEntity);
        assertEquals("12 street Fol", addressEntities.get(0).getAddress());
    }
}
