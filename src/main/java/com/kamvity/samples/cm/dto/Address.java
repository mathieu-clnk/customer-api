package com.kamvity.samples.cm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private Long addressId;

    private String address;

    private String city;

    private String name;

    private String zipCode;

    private String country;

    private Boolean isDefault;

    private Long customerId;
}
