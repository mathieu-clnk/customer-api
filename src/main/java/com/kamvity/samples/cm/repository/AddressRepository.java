package com.kamvity.samples.cm.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddressRepository {

    @PersistenceContext
    private EntityManager entityManager;
}
