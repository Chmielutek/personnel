package com.pchmielewski.personnel.logic.api.to;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressTo {

    private Long id;

    @NotNull
    private String streetName;

    @NotNull
    private int streetNumber;

    @NotNull
    private String city;

    @NotNull
    private String postalCode;

    @NotNull
    private Long employeeId;
}
