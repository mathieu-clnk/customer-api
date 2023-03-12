package com.kamvity.samples.cm.entity;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AddressEntityTest {

    public AddressEntity createAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(Long.decode("1"));
        addressEntity.setAddress("12 rue du lac");
        addressEntity.setName("Office");
        addressEntity.setCity("Lyon");
        addressEntity.setCountry("France");
        addressEntity.setZipCode("69003");
        addressEntity.setCustomer(new CustomerEntity());
        addressEntity.setIsDefault(Boolean.TRUE);

        return addressEntity;
    }

    @Test
    public void testGetter() {
        AddressEntity addressEntity = createAddress();
        assertEquals(Long.decode("1"), addressEntity.getAddressId());
        assertEquals("12 rue du lac", addressEntity.getAddress());
        assertEquals("Office", addressEntity.getName());
        assertEquals("Lyon", addressEntity.getCity());
        assertEquals("France", addressEntity.getCountry());
        assertEquals("69003", addressEntity.getZipCode());
        assertEquals(Boolean.FALSE, addressEntity.getCustomer().getIsCompany());
        assert addressEntity.getIsDefault();
    }

    @Test
    public void testSetter() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressId(Long.decode("1"));
        addressEntity.setAddress("12 rue du lac");
        addressEntity.setName("Office");
        addressEntity.setCity("Lyon");
        addressEntity.setCountry("France");
        addressEntity.setZipCode("69003");
        addressEntity.setCustomer(new CustomerEntity());
        addressEntity.setIsDefault(Boolean.TRUE);

        assertEquals(Long.decode("1"), addressEntity.getAddressId());
        assertEquals("12 rue du lac", addressEntity.getAddress());
        assertEquals("Office", addressEntity.getName());
        assertEquals("Lyon", addressEntity.getCity());
        assertEquals("France", addressEntity.getCountry());
        assertEquals("69003", addressEntity.getZipCode());
        assertEquals(Boolean.FALSE, addressEntity.getCustomer().getIsCompany());
    }
}
