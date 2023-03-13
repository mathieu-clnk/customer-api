package com.kamvity.samples.cm.repository;

import com.kamvity.samples.cm.entity.AddressEntity;
import com.kamvity.samples.cm.entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class CustomerJpaRepositoryTest {

    @Autowired
    CustomerJpaRepository customerJpaRepository;

    @Autowired
    AddressJpaRepository addressJpaRepository;

    public AddressEntity createAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(Long.decode("1"));
        addressEntity.setAddress("Borner street 535");
        addressEntity.setName("Office");
        addressEntity.setCity("Amsterdam");
        addressEntity.setCountry("Netherland");
        addressEntity.setZipCode("1019");
        //addressEntity.setCustomerEntity(new CustomerEntity());
        addressEntity.setIsDefault(Boolean.TRUE);

        return addressEntity;
    }
    @Test
    public void testSave() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail("anne.frank@email.com");
        customerEntity.setLastname("Frank");
        customerEntity.setTitle("Mrs");
        customerEntity.setFirstname("Anne");
        assertEquals("Anne",customerJpaRepository.save(customerEntity).getFirstname());
        AddressEntity address = createAddress();
        address.setCustomer(customerEntity);
        AddressEntity addressEntity = addressJpaRepository.save(address);
        assertEquals("Amsterdam",addressEntity.getCity());
        assertEquals("Anne",addressEntity.getCustomer().getFirstname());
        List<AddressEntity> addressEntities = new ArrayList<>();
        addressEntities.add(address);
        customerEntity.setAddressEntities(addressEntities);
        assertEquals("Amsterdam",customerJpaRepository.save(customerEntity).getAddressEntities().get(0).getCity());
        assertEquals("Anne",customerJpaRepository.findAll().get(0).getFirstname());

    }
    @Test
    @Sql("/delete-customer.sql")
    @Sql("/create-customer.sql")
    public void testFindByEmail() {
        assertEquals("Marie",customerJpaRepository.findByEmail("marie.curie@email.org").get().getFirstname());
        assertTrue(customerJpaRepository.findByEmail("does.not.exist").isEmpty());
    }

}
