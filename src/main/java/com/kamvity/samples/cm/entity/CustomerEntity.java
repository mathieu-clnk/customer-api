package com.kamvity.samples.cm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(length = 8,nullable = false)
    private String title;

    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private Boolean isCompany = Boolean.FALSE;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AddressEntity> addressEntities = new ArrayList<>();

}
