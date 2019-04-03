package com.pchmielewski.personnel.logic.impl.usecase;

import com.pchmielewski.personnel.dataaccess.EmployeeEntity;
import com.pchmielewski.personnel.dataaccess.repository.AddressRepository;
import com.pchmielewski.personnel.dataaccess.repository.EmployeeRepository;
import com.pchmielewski.personnel.logic.api.to.AddressTo;
import com.pchmielewski.personnel.logic.api.to.EmployeeTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaRepositories(basePackageClasses = {EmployeeRepository.class, AddressRepository.class})
@Transactional
public class UcManageEmployeeImplTest {

    @Autowired
    private UcManageEmployeeImpl ucManageEmployee;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void addEmployeeTest() {

        // given
        EmployeeTo to = new EmployeeTo();
        to.setFirstName("Jan");
        to.setLastName("Kowalski");

        // when
        EmployeeTo savedEmployee = this.ucManageEmployee.addEmployee(to);

        // then
        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getFirstName()).isEqualTo(to.getFirstName());
        assertThat(savedEmployee.getLastName()).isEqualTo(to.getLastName());
    }

    @Test
    public void addAddressToEmployeeTest() {

        // given
        EmployeeEntity entity = new EmployeeEntity();
        entity.setFirstName("Jan");
        entity.setLastName("Kowalski");
        entity = this.employeeRepository.save(entity);

        AddressTo address = new AddressTo();
        address.setStreetName("Losowa");
        address.setStreetNumber(1);
        address.setEmployeeId(entity.getId());

        // when
        AddressTo savedAddress = this.ucManageEmployee.addAddressToEmployee(address);

        // then
        assertThat(savedAddress.getId()).isNotNull();
    }
}
