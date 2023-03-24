package com.kamvity.samples.cm.controller;

import com.kamvity.samples.cm.dto.Customer;
import com.kamvity.samples.cm.response.Response;
import com.kamvity.samples.cm.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "customer-controller", description = "Customer API")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/create",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Create customer",description = "Create a new customer")
    /*
    @ApiResponses(value = {
            @ApiResponse(useReturnTypeSchema = true,responseCode = "200", description = "Customer created successfully", content = @Content(
                    schema = @Schema(ref = "#/components/schemas/ResponseCustomer"),
                    examples = @ExampleObject(
                            name = "Success response",
                            value = "{"+
                                    "\"status\": \"success\","+
                                    "\"errorMessage\": \"\"," +
                                    "\"sensitiveMessage\": \"\"," +
                                    "\"result\": {"+
                                    "  \"id\": 123456,"+
                                    "  \"title\": \"Mrs\","+
                                    "  \"firstname\": \"Nina\","+
                                    "  \"lastname\": \"Simone\","+
                                    "  \"email\": \"nina.simone@email.org\""+
                                    "}"+
                                    "}"
                    )
            )),
            @ApiResponse(useReturnTypeSchema = true,responseCode = "400", description = "Error while creating the customer.", content = @Content(
                    schema = @Schema(ref = "#/components/schemas/ResponseCustomer"),
                    examples = @ExampleObject(
                            name = "Customer does already exist response",
                            value = "{"+
                                    "\"status\": \"failed\","+
                                    "\"errorMessage\": \"This email is already used.\"," +
                                    "\"sensitiveMessage\": \"\"," +
                                    "\"result\": {"+
                                    "  \"id\": 0,"+
                                    "  \"title\": \"\","+
                                    "  \"firstname\": \"\","+
                                    "  \"lastname\": \"\","+
                                    "  \"email\": \"\""+
                                    "}"+
                                    "}"
                    )
            ))
    })
     */
    public ResponseEntity<Response<Customer>> createCustomer(
            @Parameter(name = "customer",required = true,schema = @Schema(
                    implementation = Customer.class,
                    example = "{ " +
                    "\"id\" : \"123456\"," +
                    "\"firstname\" : \"Nkosi\"," +
                    "\"lastname\" : \"Johnson\"," +
                    "\"email\" : \"nkosi.johnson@email.org\"" +
                    "}")) @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PostMapping(path = "/get-by-email",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get customer by Email", description = "Get a customer by its email. The email is unique.")
    /*
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "A customer has been found with this email address.", content = @Content(
                    examples = @ExampleObject(
                            name = "Customer found.",
                            value = "{" +
                                    "\"status\": \"success\"," +
                                    "\"errorMessage\": \"\"," +
                                    "\"sensitiveMessage\": \"\"," +
                                    "\"result\": {" +
                                    "  \"id\": 123456," +
                                    "  \"title\": \"Mrs\"," +
                                    "  \"firstname\": \"Nina\"," +
                                    "  \"lastname\": \"Simone\"," +
                                    "  \"email\": \"nina.simone@email.org\"" +
                                    "}" +
                                    "}"
                    )
            )),
            @ApiResponse(responseCode = "404", description = "No customer has been found with this email.", content = @Content(
                    examples = @ExampleObject(
                            name = "Customer not found.",
                            value = "{" +
                                    "\"status\": \"failed\"," +
                                    "\"errorMessage\": \"Customer not found.\"," +
                                    "\"sensitiveMessage\": \"\"," +
                                    "\"result\": {" +
                                    "  \"id\": 0," +
                                    "  \"title\": \"\"," +
                                    "  \"firstname\": \"\"," +
                                    "  \"lastname\": \"\"," +
                                    "  \"email\": \"\"" +
                                    "}" +
                                    "}"
                    )
            ))
    })

     */
    public ResponseEntity<Response<Customer>> getByEmail(
            @Parameter(name = "email",required = true,schema = @Schema(example = "email.address@domain.com"))
            @RequestBody String email) {
        return customerService.findByEmail(email);
    }
    @PostMapping(path = "/get-by-id",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get customer by Id.", description = "Get a customer by id.")
    /*
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "A customer has been found with this Id.", content = @Content(
                    examples = @ExampleObject(
                            name = "Customer found.",
                            value = "{" +
                                    "\"status\": \"success\"," +
                                    "\"errorMessage\": \"\"," +
                                    "\"sensitiveMessage\": \"\"," +
                                    "\"result\": {" +
                                    "  \"id\": 123456," +
                                    "  \"title\": \"Mrs\"," +
                                    "  \"firstname\": \"Nina\"," +
                                    "  \"lastname\": \"Simone\"," +
                                    "  \"email\": \"nina.simone@email.org\"" +
                                    "}" +
                                    "}"
                    )
            )),
            @ApiResponse(responseCode = "404", description = "No customer has been found with this Id.", content = @Content(
                    examples = @ExampleObject(
                            name = "Customer not found.",
                            value = "{" +
                                    "\"status\": \"failed\"," +
                                    "\"errorMessage\": \"Customer not found.\"," +
                                    "\"sensitiveMessage\": \"\"," +
                                    "\"result\": {" +
                                    "  \"id\": 0," +
                                    "  \"title\": \"\"," +
                                    "  \"firstname\": \"\"," +
                                    "  \"lastname\": \"\"," +
                                    "  \"email\": \"\"" +
                                    "}" +
                                    "}"
                    )
            ))
    })

     */
    public ResponseEntity<Response<Customer>> getById(
            @Parameter(name = "id",required = true,schema = @Schema(example = "111111"))
            @RequestBody String id) {
        return customerService.getById(Long.decode(id));
    }

}
