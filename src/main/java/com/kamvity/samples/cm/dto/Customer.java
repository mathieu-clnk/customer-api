package com.kamvity.samples.cm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer Data Transfer Object
 * <pre>
 *     Minimal information required by external applications related to customers.
 * </pre>
 */
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
