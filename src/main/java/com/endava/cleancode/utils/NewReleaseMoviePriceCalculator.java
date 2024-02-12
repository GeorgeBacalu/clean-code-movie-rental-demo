package com.endava.cleancode.utils;

import com.endava.cleancode.model.Rental;

public class NewReleaseMoviePriceCalculator implements MoviePriceCalculator {

    @Override
    public double getPrice(Rental rental) {
        return rental.getNrDaysRented() * 3.0;
    }
}
