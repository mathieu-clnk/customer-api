package com.kamvity.samples.cm;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * CustomerEntity Management application.
 *
 * <p>
 *   This application handles all the customer's data to facilitate the GDPR compliance.
 * </p>
 * @author Mathieu C.
 */
@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
