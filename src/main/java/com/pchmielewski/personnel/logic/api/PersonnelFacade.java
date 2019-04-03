package com.pchmielewski.personnel.logic.api;

import com.pchmielewski.personnel.logic.api.to.AddressTo;
import com.pchmielewski.personnel.logic.api.to.EmployeeTo;

import java.util.List;
import java.util.Optional;

public interface PersonnelFacade {

    /**
     * Returns a list of all employees and their adressess
     *
     * @return list of all employees and their adressess
     */
    public Optional<List<EmployeeTo>> getEmployeeData();

    /**
     * Adds new Employee data
     *
     * @param employee to be added
     * @return added employee data
     */
    public EmployeeTo addEmployee(EmployeeTo employee);

    /**
     * Adds new Address data to existing Employee
     *
     * @param address new address data
     * @return added address data
     */
    public AddressTo addAddressToEmployee(AddressTo address);
}
