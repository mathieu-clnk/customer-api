package com.kamvity.samples.cm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
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
@OpenAPIDefinition(
        info = @Info(
                title = "Customer API application",
                description = "API to manage customer",
                version = "v0",
                contact = @Contact(name = "Mathieu", email = "mathieu.kamvity@gmail.com")
        ),
        servers = @Server(
                url = "https://customer.samples.kamvity.com",
                description = "Test environment."
        )
)
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
