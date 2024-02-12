package com.endava.cleancode.service;

import com.endava.cleancode.model.Customer;
import com.endava.cleancode.model.Rental;

public interface CustomerService {

    double getTotalPrice(String customerName);

    double getRentalPrice(Rental rental);

    int getFrequentRenterPoints(String customerName);

    Customer getCustomerByName(String name);
}
