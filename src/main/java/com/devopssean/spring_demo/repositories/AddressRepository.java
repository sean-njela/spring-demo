package com.devopssean.spring_demo.repositories;

import com.devopssean.spring_demo.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}