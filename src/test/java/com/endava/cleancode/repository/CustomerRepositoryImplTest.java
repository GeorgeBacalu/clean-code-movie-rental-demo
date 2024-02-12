package com.endava.cleancode.repository;

import com.endava.cleancode.exception.ResourceNotFoundException;
import com.endava.cleancode.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;
import static com.endava.cleancode.mock.CustomerMock.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@SpringBootTest
class CustomerRepositoryImplTest {

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customer1 = getMockedCustomer1();
        customer2 = getMockedCustomer2();
        customer3 = getMockedCustomer3();
        customers = getMockedCustomers();
        customerRepository.deleteAll();
        customerRepository.saveOrUpdate(customer1);
        customerRepository.saveOrUpdate(customer2);
    }

    @Test
    void findAll_test() {
        then(customerRepository.findAll()).isEqualTo(customers);
    }

    @Test
    void findByName_test() {
        then(customerRepository.findByName("Customer1")).isEqualTo(Optional.of(customer1));
    }

    @Test
    void saveOrUpdate_forSave_test() {
        then(customerRepository.saveOrUpdate(customer3)).isEqualTo(customer3);
        then(customerRepository.findAll()).isEqualTo(List.of(customer1, customer2, customer3));
    }

    @Test
    void saveOrUpdate_forUpdate_test() {
        then(customerRepository.saveOrUpdate(customer1)).isEqualTo(customer1);
        then(customerRepository.findAll()).isEqualTo(customers);
    }

    @Test
    void delete_validCustomer_test() {
        customerRepository.delete(customer1);
        then(customerRepository.findAll()).isEqualTo(List.of(customer2));
    }

    @Test
    void delete_invalidCustomer_test() {
        thenThrownBy(() -> customerRepository.delete(customer3))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, customer3.getName()));
    }
}
