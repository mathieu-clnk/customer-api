package com.kamvity.samples.cm.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerEntityTest {

    public CustomerEntity createCustomer() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(Long.decode("1"));
        customerEntity.setEmail("m@email.org");
        customerEntity.setTitle("Mr");
        customerEntity.setLastname("C");
        customerEntity.setFirstname("M");
        customerEntity.setIsCompany(Boolean.TRUE);
        AddressEntity addressEntity1 = new AddressEntity(Long.decode("1"),"Home","1 street Freedom","Delhi","22113","India",Boolean.TRUE, customerEntity);
        AddressEntity addressEntity2 = new AddressEntity(Long.decode("2"),"Office","2 street Peace","Montreal","YDQ213","Canada",Boolean.FALSE, customerEntity);
        List<AddressEntity> addressEntities = new ArrayList<>();
        addressEntities.add(addressEntity1);
        addressEntities.add(addressEntity2);
        customerEntity.setAddressEntities(addressEntities);
        return customerEntity;
    }

    @Test
    public void testGetter() {
        CustomerEntity customerEntity = createCustomer();
        assertEquals(Long.decode("1"), customerEntity.getId());
        assertEquals("m@email.org", customerEntity.getEmail());
        assertEquals("Mr", customerEntity.getTitle());
        assertEquals("C", customerEntity.getLastname());
        assertEquals("M", customerEntity.getFirstname());
        assert customerEntity.getIsCompany();
        assertEquals("Home",
                customerEntity.getAddressEntities().stream().filter(a -> a.getAddressId().equals(Long.decode("1"))).findFirst().get().getName());
    }

    @Test
    public void testSetter() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(Long.decode("1"));
        customerEntity.setEmail("m@email.org");
        customerEntity.setTitle("Mr");
        customerEntity.setLastname("C");
        customerEntity.setFirstname("M");
        assertEquals(Long.decode("1"), customerEntity.getId());
        assertEquals("m@email.org", customerEntity.getEmail());
        assertEquals("Mr", customerEntity.getTitle());
        assertEquals("C", customerEntity.getLastname());
        assertEquals("M", customerEntity.getFirstname());
        assertEquals(Boolean.FALSE, customerEntity.getIsCompany());
        customerEntity.setIsCompany(Boolean.TRUE);
        assert customerEntity.getIsCompany();
        AddressEntity addressEntity1 = new AddressEntity(Long.decode("1"),"Home","1 street Freedom","Delhi","22113","India",Boolean.TRUE, customerEntity);
        AddressEntity addressEntity2 = new AddressEntity(Long.decode("2"),"Office","2 street Peace","Montreal","YDQ213","Canada",Boolean.FALSE, customerEntity);
        List<AddressEntity> addressEntities = new ArrayList<>();
        addressEntities.add(addressEntity1);
        addressEntities.add(addressEntity2);
        customerEntity.setAddressEntities(addressEntities);
        assertEquals("Office",
                customerEntity.getAddressEntities().stream().filter(a -> a.getAddressId().equals(Long.decode("2"))).findFirst().get().getName());
    }
    @Test
    public void testSetterConstructor() {
        CustomerEntity customerEntity = new CustomerEntity(Long.decode("1"),"Mr","M","C",Boolean.FALSE,"m@email.org",new ArrayList<>());
        assertEquals("M", customerEntity.getFirstname());
        assertEquals(Boolean.FALSE, customerEntity.getIsCompany());
    }
}
