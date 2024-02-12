package com.endava.cleancode.mock;

import com.endava.cleancode.model.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

import static com.endava.cleancode.mock.RentalMock.getMockedRentals1;
import static com.endava.cleancode.mock.RentalMock.getMockedRentals2;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMock {

    public static List<Customer> getMockedCustomers() {
        return List.of(getMockedCustomer1(), getMockedCustomer2());
    }

    public static Customer getMockedCustomer1() {
        return Customer.builder().name("Customer1").rentals(getMockedRentals1()).build();
    }

    public static Customer getMockedCustomer2() {
        return Customer.builder().name("Customer2").rentals(getMockedRentals2()).build();
    }

    public static Customer getMockedCustomer3() {
        return Customer.builder().name("Customer3").rentals(Collections.emptyList()).build();
    }
}
