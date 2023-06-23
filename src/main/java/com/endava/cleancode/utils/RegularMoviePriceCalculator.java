package com.endava.cleancode.utils;

import com.endava.cleancode.model.Rental;

public class RegularMoviePriceCalculator implements MoviePriceCalculator {

    @Override
    public double getPrice(Rental rental) {
        double price = 2;
        if (rental.getNrDaysRented() > 2) {
            price += (rental.getNrDaysRented() - 2) * 1.5;
        }
        return price;
    }
}
