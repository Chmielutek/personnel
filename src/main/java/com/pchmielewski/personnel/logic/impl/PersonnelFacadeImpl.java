package com.pchmielewski.personnel.logic.impl;

import com.pchmielewski.personnel.logic.api.PersonnelFacade;
import com.pchmielewski.personnel.logic.api.to.AddressTo;
import com.pchmielewski.personnel.logic.api.to.EmployeeTo;
import com.pchmielewski.personnel.logic.api.usecase.UcManageEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonnelFacadeImpl implements PersonnelFacade {

    private final UcManageEmployee ucManageEmployee;

    /**
     * Constructs a {@link PersonnelFacadeImpl}
     *
     * @param ucManageEmployee a UC for managing the employees and their related data
     */
    @Autowired
    public PersonnelFacadeImpl(UcManageEmployee ucManageEmployee) {
        this.ucManageEmployee = ucManageEmployee;
    }

    @Override
    public Optional<List<EmployeeTo>> getEmployeeData() {
        return this.ucManageEmployee.getEmployeeData();
    }

    @Override
    public EmployeeTo addEmployee(EmployeeTo employee) {
        return this.ucManageEmployee.addEmployee(employee);
    }

    @Override
    public AddressTo addAddressToEmployee(AddressTo address) {
        return this.ucManageEmployee.addAddressToEmployee(address);
    }
}
