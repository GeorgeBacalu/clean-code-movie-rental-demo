package com.endava.cleancode.utils;

import com.endava.cleancode.model.Rental;

public interface MoviePriceCalculator {

    double getPrice(Rental rental);
}
