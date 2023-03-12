package com.kamvity.samples.cm.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfigTest {

    @Value("${openapi.version}")
    private String apiVersion;

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("CustomerEntity Application")
                        .description("Application to manage all customers.")
                        .version(apiVersion)
                        .contact(new Contact().email("mathieu.kamvity@gmail.com"))
                        .license(new License().name("Apache 2.0")
                                .url("http://customer.demo.kamvity.com"))

                );

    }
}
