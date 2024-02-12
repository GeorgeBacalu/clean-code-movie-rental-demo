package com.endava.cleancode.repository;

import com.endava.cleancode.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findByName(String name);

    Customer saveOrUpdate(Customer customer);

    void delete(Customer customer);

    void deleteAll();
}
