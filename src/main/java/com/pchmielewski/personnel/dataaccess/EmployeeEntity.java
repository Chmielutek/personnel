package com.pchmielewski.personnel.dataaccess;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "employee")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AddressEntity> addressList;
}
