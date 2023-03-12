package com.kamvity.samples.cm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;

    private String title;

    private String firstname;

    private String lastname;

    private String email;
}
