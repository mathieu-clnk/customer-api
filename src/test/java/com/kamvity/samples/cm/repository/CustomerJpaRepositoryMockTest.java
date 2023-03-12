package com.kamvity.samples.cm.repository;

import com.kamvity.samples.cm.config.CustomerConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = CustomerConfig.class)
public class CustomerJpaRepositoryMockTest {

    @MockBean
    CustomerJpaRepository customerJpaRepository;

    @Autowired
    ApplicationContext context;

    @Test
    public void testCustomerCount() {
        Mockito.when(customerJpaRepository.count()).thenReturn(Long.decode("2"));

        CustomerJpaRepository customerRepositoryContext = context.getBean(CustomerJpaRepository.class);
        Long customerCount = customerRepositoryContext.count();
        assertEquals(Long.decode("2"),customerCount);
    }


}
