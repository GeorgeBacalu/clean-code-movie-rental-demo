package com.endava.cleancode.repository;

import com.endava.cleancode.exception.ResourceNotFoundException;
import com.endava.cleancode.model.Customer;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;
import static com.endava.cleancode.mock.CustomerMock.getMockedCustomer1;
import static com.endava.cleancode.mock.CustomerMock.getMockedCustomer2;
import static com.endava.cleancode.mock.CustomerMock.getMockedCustomer3;
import static com.endava.cleancode.mock.CustomerMock.getMockedCustomers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @AfterEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Test
    void findAll_shouldReturnAllCustomers() {
        assertThat(customerRepository.findAll()).isEqualTo(customers);
    }

    @Test
    void findByName_shouldReturnCustomerWithGivenName() {
        assertThat(customerRepository.findByName("Customer1")).isEqualTo(Optional.of(customer1));
    }

    @Test
    void saveOrUpdate_shouldUpdateCustomer() {
        assertThat(customerRepository.saveOrUpdate(customer1)).isEqualTo(customer1);
        assertThat(customerRepository.findAll()).isEqualTo(List.of(customer1, customer2));
    }

    @Test
    void saveOrUpdate_shouldAddCustomerToList() {
        assertThat(customerRepository.saveOrUpdate(customer3)).isEqualTo(customer3);
        assertThat(customerRepository.findAll()).isEqualTo(List.of(customer1, customer2, customer3));
    }

    @Test
    void delete_shouldRemoveCustomerFromList() {
        customerRepository.delete(customer1);
        assertThat(customerRepository.findAll()).isEqualTo(List.of(customer2));
    }

    @Test
    void delete_shouldThrowException() {
        assertThatThrownBy(() -> customerRepository.delete(customer3))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, customer3.getName()));
    }
}
