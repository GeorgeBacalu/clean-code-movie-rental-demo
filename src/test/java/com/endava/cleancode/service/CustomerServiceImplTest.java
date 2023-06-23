package com.endava.cleancode.service;

import com.endava.cleancode.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;
import static com.endava.cleancode.constants.Constants.INVALID_CUSTOMER_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    void getTotalPrice_withValidCustomerName_shouldReturnTotalRentalsPriceForCustomer1() {
        assertThat(customerService.getTotalPrice("Customer1")).isEqualTo(6.5);
    }

    @Test
    void getTotalPrice_withValidCustomerName_shouldReturnTotalRentalsPriceForCustomer2() {
        assertThat(customerService.getTotalPrice("Customer2")).isEqualTo(12.5);
    }

    @Test
    void getTotalPrice_withInvalidCustomerName_shouldThrowException() {
        assertThatThrownBy(() -> customerService.getTotalPrice(INVALID_CUSTOMER_NAME))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, INVALID_CUSTOMER_NAME));
    }

    @Test
    void getFrequentRenterPoints_withValidCustomerName_shouldReturnFrequentRenterPointsForCustomer1() {
        assertThat(customerService.getFrequentRenterPoints("Customer1")).isEqualTo(3);
    }

    @Test
    void getFrequentRenterPoints_withValidCustomerName_shouldReturnFrequentRenterPointsForCustomer2() {
        assertThat(customerService.getFrequentRenterPoints("Customer2")).isEqualTo(4);
    }

    @Test
    void getFrequentRenterPoints_withInvalidCustomerName_shouldThrowException() {
        assertThatThrownBy(() -> customerService.getFrequentRenterPoints(INVALID_CUSTOMER_NAME))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, INVALID_CUSTOMER_NAME));
    }
}
