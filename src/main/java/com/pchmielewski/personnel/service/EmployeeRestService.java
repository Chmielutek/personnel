package com.pchmielewski.personnel.service;

import com.pchmielewski.personnel.logic.api.PersonnelFacade;
import com.pchmielewski.personnel.logic.api.to.AddressTo;
import com.pchmielewski.personnel.logic.api.to.EmployeeTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("services/rest/employee/v1")
public class EmployeeRestService {

    private final PersonnelFacade personnelFacade;

    /**
     * Constructs a {@link EmployeeRestService}
     *
     * @param personnelFacade a personnel facade
     */
    @Autowired
    public EmployeeRestService(PersonnelFacade personnelFacade) {
        this.personnelFacade = personnelFacade;
    }

    /**
     * Returns a list of all employees
     *
     * @return list of all employees
     */
    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeTo>> getAllEmployees() {
        Optional<List<EmployeeTo>> employees = this.personnelFacade.getEmployeeData();

        if (employees.isPresent()) {
            return ResponseEntity.ok(employees.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Adds a new employee
     *
     * @param to                DTO containing information on new employee
     * @param result            BindingResult object, allows to apply validator
     * @return                  The added employee
     * @throws BindException    in case of validation errors
     */
    @RequestMapping(value = "add-employee", method = RequestMethod.POST)
    public ResponseEntity<EmployeeTo> addEmployee(@Valid @RequestBody EmployeeTo to, BindingResult result)
        throws BindException {

        if (result.hasErrors()) {
            throw new BindException(result);
        } else {
            EmployeeTo addedEmployee = personnelFacade.addEmployee(to);
            return ResponseEntity.ok(addedEmployee);
        }
    }

    /**
     * Adds a new employee
     *
     * @param to                DTO containing information on new address to be added to employee
     * @param result            BindingResult object, allows to apply validator
     * @return                  Added address data
     * @throws BindException    in case of validation errors
     */
    @RequestMapping(value = "add-address", method = RequestMethod.POST)
    public ResponseEntity<AddressTo> addAddressToEmployee(@Valid @RequestBody AddressTo to, BindingResult result)
            throws BindException {

        if (result.hasErrors()) {
            throw new BindException(result);
        } else {
            AddressTo updatedEmployee = personnelFacade.addAddressToEmployee(to);
            return ResponseEntity.ok(updatedEmployee);
        }
    }
}
