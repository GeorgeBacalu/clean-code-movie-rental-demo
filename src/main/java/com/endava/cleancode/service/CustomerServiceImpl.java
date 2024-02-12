package com.endava.cleancode.service;

import com.endava.cleancode.enums.PriceCode;
import com.endava.cleancode.exception.ResourceNotFoundException;
import com.endava.cleancode.model.Customer;
import com.endava.cleancode.model.Rental;
import com.endava.cleancode.repository.CustomerRepository;
import com.endava.cleancode.utils.ChildrenMoviePriceCalculator;
import com.endava.cleancode.utils.MoviePriceCalculator;
import com.endava.cleancode.utils.NewReleaseMoviePriceCalculator;
import com.endava.cleancode.utils.RegularMoviePriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MoviePriceCalculator regularMoviePriceCalculator = new RegularMoviePriceCalculator();
    private final MoviePriceCalculator newReleaseMoviePriceCalculator = new NewReleaseMoviePriceCalculator();
    private final MoviePriceCalculator childrenMoviePriceCalculator = new ChildrenMoviePriceCalculator();

    @Override
    public double getTotalPrice(String customerName) {
        return getCustomerByName(customerName).getRentals().stream().mapToDouble(this::getRentalPrice).sum();
    }

    @Override
    public double getRentalPrice(Rental rental) {
        MoviePriceCalculator moviePriceCalculator = switch (rental.getMovie().getPriceCode()) {
            case REGULAR -> regularMoviePriceCalculator;
            case NEW_RELEASE -> newReleaseMoviePriceCalculator;
            case CHILDREN -> childrenMoviePriceCalculator;
        };
        return moviePriceCalculator.getPrice(rental);
    }

    @Override
    public int getFrequentRenterPoints(String customerName) {
        List<Rental> rentals = getCustomerByName(customerName).getRentals();
        int frequentRenterPoints = 0;
        for (Rental rental : rentals) {
            ++frequentRenterPoints;
            if (rental.getMovie().getPriceCode() == PriceCode.NEW_RELEASE && rental.getNrDaysRented() > 1) {
                ++frequentRenterPoints;
            }
        }
        return frequentRenterPoints;
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND, name)));
    }
}
