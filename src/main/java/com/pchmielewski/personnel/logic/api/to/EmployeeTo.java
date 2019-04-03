package com.pchmielewski.personnel.logic.api.to;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeTo {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private List<AddressTo> addressList;
}
