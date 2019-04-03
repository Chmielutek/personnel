package com.pchmielewski.personnel.dataaccess.repository;

import com.pchmielewski.personnel.dataaccess.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
