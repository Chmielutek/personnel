package com.pchmielewski.personnel.dataaccess;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "address")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String streetName;

    private int streetNumber;

    private String city;

    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
