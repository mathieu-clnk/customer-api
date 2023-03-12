package com.kamvity.samples.cm.repository;

import com.kamvity.samples.cm.entity.AddressEntity;
import com.kamvity.samples.cm.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressJpaRepository extends JpaRepository<AddressEntity,Long> {

    List<AddressEntity> getAddressesByCustomer(CustomerEntity customerEntity);
}
