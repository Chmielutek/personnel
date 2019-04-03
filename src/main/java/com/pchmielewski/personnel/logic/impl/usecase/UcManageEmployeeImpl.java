package com.pchmielewski.personnel.logic.impl.usecase;

import com.pchmielewski.personnel.dataaccess.AddressEntity;
import com.pchmielewski.personnel.dataaccess.EmployeeEntity;
import com.pchmielewski.personnel.dataaccess.repository.AddressRepository;
import com.pchmielewski.personnel.dataaccess.repository.EmployeeRepository;
import com.pchmielewski.personnel.logic.api.to.AddressTo;
import com.pchmielewski.personnel.logic.api.to.EmployeeTo;
import com.pchmielewski.personnel.logic.api.usecase.UcManageEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UcManageEmployeeImpl implements UcManageEmployee {

    private final EmployeeRepository employeeRepository;

    private final AddressRepository addressRepository;

    /**
     * Constructor method for UcManageEmployeeImpl
     *
     * @param employeeRepository refers to repository where Employee related data is stored
     * @param addressRepository refers to repository where Address related data is stored
     */
    @Autowired
    public UcManageEmployeeImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<List<EmployeeTo>> getEmployeeData() {

        List<EmployeeTo> employees = this.employeeRepository.findAll().stream()
                .map(e -> mapEmployeeEntityToTo(e))
                .collect(Collectors.toList());

        return Optional.of(employees);
    }

    @Override
    public EmployeeTo addEmployee(EmployeeTo employee) {

        EmployeeEntity employeeEntity = this.employeeRepository.save(mapEmployeeToToEntity(employee));

        return mapEmployeeEntityToTo(employeeEntity);
    }

    @Override
    public AddressTo addAddressToEmployee(AddressTo address) {

        return mapAddressEntityToTo(this.addressRepository.save(mapAddressToToEntity(address)));
    }

    private EmployeeTo mapEmployeeEntityToTo(EmployeeEntity entity) {
        EmployeeTo to = new EmployeeTo();

        to.setId(entity.getId());
        to.setFirstName(entity.getFirstName());
        to.setLastName(entity.getLastName());

        if (entity.getAddressList() != null) {
            to.setAddressList(entity.getAddressList().stream()
                    .map(addressEntity -> mapAddressEntityToTo(addressEntity))
                    .collect(Collectors.toList()));
        }

        return to;
    }

    private EmployeeEntity mapEmployeeToToEntity(EmployeeTo to) {
        EmployeeEntity entity = new EmployeeEntity();

        entity.setId(to.getId());
        entity.setFirstName(to.getFirstName());
        entity.setLastName(to.getLastName());

        if (to.getAddressList() != null) {
            entity.setAddressList(to.getAddressList()
                    .stream()
                    .map(address -> mapAddressToToEntity(address))
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    private AddressTo mapAddressEntityToTo(AddressEntity entity) {
        AddressTo to = new AddressTo();

        to.setId(entity.getId());
        to.setStreetNumber(entity.getStreetNumber());
        to.setStreetName(entity.getStreetName());
        to.setCity(entity.getCity());
        to.setPostalCode(entity.getPostalCode());
        to.setEmployeeId(entity.getEmployee().getId());

        return to;
    }

    private AddressEntity mapAddressToToEntity(AddressTo to) {

            AddressEntity entity = new AddressEntity();
            entity.setId(to.getId());
            entity.setStreetName(to.getStreetName());
            entity.setStreetNumber(to.getStreetNumber());
            entity.setCity(to.getCity());
            entity.setPostalCode(to.getPostalCode());

            entity.setEmployee(this.employeeRepository.findById((to.getEmployeeId())).get());
            return entity;
    }
}
