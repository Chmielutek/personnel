package com.pchmielewski.personnel.dataaccess.repository;

import com.pchmielewski.personnel.dataaccess.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
