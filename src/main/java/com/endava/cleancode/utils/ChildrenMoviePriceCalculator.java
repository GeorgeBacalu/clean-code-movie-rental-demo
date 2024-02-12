package com.endava.cleancode.utils;

import com.endava.cleancode.model.Rental;

public class ChildrenMoviePriceCalculator implements MoviePriceCalculator {

    @Override
    public double getPrice(Rental rental) {
        double price = 1.5;
        if (rental.getNrDaysRented() > 3) {
            price += (rental.getNrDaysRented() - 3) * 1.5;
        }
        return price;
    }
}
