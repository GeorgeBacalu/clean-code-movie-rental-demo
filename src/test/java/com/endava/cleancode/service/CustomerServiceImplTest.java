package com.endava.cleancode.service;

import com.endava.cleancode.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;
import static com.endava.cleancode.constants.Constants.INVALID_CUSTOMER_NAME;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void getTotalPrice_customer1_test() {
        then(customerService.getTotalPrice("Customer1")).isEqualTo(6.5);
    }

    @Test
    void getTotalPrice_customer2_test() {
        then(customerService.getTotalPrice("Customer2")).isEqualTo(12.5);
    }

    @Test
    void getTotalPrice_invalidCustomer_test() {
        thenThrownBy(() -> customerService.getTotalPrice(INVALID_CUSTOMER_NAME))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, INVALID_CUSTOMER_NAME));
    }

    @Test
    void getFrequentRenterPoints_customer1_test() {
        then(customerService.getFrequentRenterPoints("Customer1")).isEqualTo(3);
    }

    @Test
    void getFrequentRenterPoints_customer2_test() {
        then(customerService.getFrequentRenterPoints("Customer2")).isEqualTo(4);
    }

    @Test
    void getFrequentRenterPoints_invalidCustomer_test() {
        thenThrownBy(() -> customerService.getFrequentRenterPoints(INVALID_CUSTOMER_NAME))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, INVALID_CUSTOMER_NAME));
    }
}
