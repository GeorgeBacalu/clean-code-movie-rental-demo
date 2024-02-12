package com.endava.cleancode.service;

import com.endava.cleancode.exception.ResourceNotFoundException;
import com.endava.cleancode.repository.CustomerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;
import static com.endava.cleancode.constants.Constants.INVALID_CUSTOMER_NAME;
import static com.endava.cleancode.mock.CustomerMock.getMockedCustomer1;
import static com.endava.cleancode.mock.CustomerMock.getMockedCustomer2;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@SpringBootTest
class RentalReportServiceImplTest {

    @Autowired
    private RentalReportServiceImpl rentalReportService;

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
        customerRepository.saveOrUpdate(getMockedCustomer1());
        customerRepository.saveOrUpdate(getMockedCustomer2());
    }

    @Test
    void generateTextReport_customer1_test() {
        then(rentalReportService.generateTextReport("Customer1")).isEqualTo("""
              Rental Record for Customer1:
              Regular Movie1: 2.0
              New Release Movie1: 3.0
              Children Movie1: 1.5
              Amount owed: 6.5
              You earned 3 frequent renter points""");
    }

    @Test
    void generateTextReport_customer2_test() {
        then(rentalReportService.generateTextReport("Customer2")).isEqualTo("""
              Rental Record for Customer2:
              Regular Movie2: 3.5
              New Release Movie2: 6.0
              Children Movie2: 3.0
              Amount owed: 12.5
              You earned 4 frequent renter points""");
    }

    @Test
    void generateTextReport_invalidCustomer_test() {
        thenThrownBy(() -> rentalReportService.generateTextReport(INVALID_CUSTOMER_NAME))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, INVALID_CUSTOMER_NAME));
    }

    @Test
    void generateHtmlReport_customer1_test() {
        then(rentalReportService.generateHtmlReport("Customer1")).isEqualTo("""
              <!DOCTYPE html>
              <html lang="en">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <meta http-equiv="X-UA-Compatible" content="ie=edge">
                  <title>Movie Rental</title>
                </head>
                <body>
                  <ul>
                    <li>Regular Movie1: 2.0</li>
                    <li>New Release Movie1: 3.0</li>
                    <li>Children Movie1: 1.5</li>
                  </ul>
                  <h3>Amount owed: 6.5</h3>
                  <h3>You earned 3 frequent renter points</h3>
                </body>
              </html>""");
    }

    @Test
    void generateHtmlReport_customer2_test() {
        then(rentalReportService.generateHtmlReport("Customer2")).isEqualTo("""
              <!DOCTYPE html>
              <html lang="en">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <meta http-equiv="X-UA-Compatible" content="ie=edge">
                  <title>Movie Rental</title>
                </head>
                <body>
                  <ul>
                    <li>Regular Movie2: 3.5</li>
                    <li>New Release Movie2: 6.0</li>
                    <li>Children Movie2: 3.0</li>
                  </ul>
                  <h3>Amount owed: 12.5</h3>
                  <h3>You earned 4 frequent renter points</h3>
                </body>
              </html>""");
    }

    @Test
    void generateHtmlReport_invalidCustomer_test() {
        thenThrownBy(() -> rentalReportService.generateHtmlReport(INVALID_CUSTOMER_NAME))
              .isInstanceOf(ResourceNotFoundException.class)
              .hasMessage(String.format(CUSTOMER_NOT_FOUND, INVALID_CUSTOMER_NAME));
    }
}
