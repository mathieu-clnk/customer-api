package com.kamvity.samples.cm.repository;

import com.kamvity.samples.cm.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity,Long> {

    List<CustomerEntity> findByLastname(String lastname);

    Optional<CustomerEntity> findByEmail(String email);

    Optional<CustomerEntity> findById(Long id);
}
