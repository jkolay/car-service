package com.carlease.car.repository;

import com.carlease.car.persistence.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByEmail(String email);
}