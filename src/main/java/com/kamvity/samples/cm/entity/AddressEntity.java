package com.kamvity.samples.cm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long addressId;

    @Column(name="addressName",length = 64,nullable = false)
    private String name;

    @Column(length = 512,nullable = false)
    private String address;

    @Column(length = 256,nullable = false)
    private String city;

    @Column(name="zipcode",length = 16,nullable = false)
    private String zipCode;

    @Column(name="country",length = 64,nullable = false)
    private String country;

    private Boolean isDefault;
    //@JoinColumn(name = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CustomerEntity customer;
}
