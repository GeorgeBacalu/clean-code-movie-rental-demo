package com.endava.cleancode;

import com.endava.cleancode.model.Customer;
import com.endava.cleancode.model.Movie;
import com.endava.cleancode.enums.PriceCode;
import com.endava.cleancode.model.Rental;
import com.endava.cleancode.repository.CustomerRepository;
import com.endava.cleancode.repository.CustomerRepositoryImpl;
import com.endava.cleancode.repository.MovieRepository;
import com.endava.cleancode.repository.MovieRepositoryImpl;
import com.endava.cleancode.repository.RentalRepository;
import com.endava.cleancode.repository.RentalRepositoryImpl;
import com.endava.cleancode.service.RentalReportService;
import com.endava.cleancode.service.RentalReportServiceImpl;
import com.endava.cleancode.service.CustomerService;
import com.endava.cleancode.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {
    private static final MovieRepository movieRepository = new MovieRepositoryImpl();
    private static final RentalRepository rentalRepository = new RentalRepositoryImpl(movieRepository);
    private static final CustomerRepository customerRepository = new CustomerRepositoryImpl(rentalRepository);
    private static final CustomerService customerService = new CustomerServiceImpl(customerRepository);
    private static final RentalReportService rentalReportService = new RentalReportServiceImpl(customerService);

    private static final String MOVIE_TITLE_REGULAR = "Regular Movie";
    private static final String MOVIE_TITLE_NEW_RELEASE = "New Release Movie";
    private static final String MOVIE_TITLE_CHILDREN = "Children Movie";
    private static final String CUSTOMER_NAME = "Customer";

    private static final Customer customer = new Customer(CUSTOMER_NAME);
    private static final Movie movieRegular = new Movie(MOVIE_TITLE_REGULAR, PriceCode.REGULAR);
    private static final Movie movieNewRelease = new Movie(MOVIE_TITLE_NEW_RELEASE, PriceCode.NEW_RELEASE);
    private static final Movie movieChildren = new Movie(MOVIE_TITLE_CHILDREN, PriceCode.CHILDREN);
    private static final Rental rentalRegular1 = new Rental(movieRegular, 2);
    private static final Rental rentalNewRelease1 = new Rental(movieNewRelease, 1);
    private static final Rental rentalChildren1 = new Rental(movieChildren, 3);
    private static final Rental rentalRegular2 = new Rental(movieRegular, 3);
    private static final Rental rentalNewRelease2 = new Rental(movieNewRelease, 2);
    private static final Rental rentalChildren2 = new Rental(movieChildren, 4);

    @BeforeEach
    void setUp() {
        customer.getRentals().clear();
    }

    @Test
    void testMoviesWithNoExtraChargesAndNoAdditionalRenterPoints() {
        customer.getRentals().add(rentalRegular1);
        customer.getRentals().add(rentalNewRelease1);
        customer.getRentals().add(rentalChildren1);
        String result = "Rental Record for " + CUSTOMER_NAME + "\n" +
              "\t" + MOVIE_TITLE_REGULAR + "\t2.0\n" +
              "\t" + MOVIE_TITLE_NEW_RELEASE + "\t3.0\n" +
              "\t" + MOVIE_TITLE_CHILDREN + "\t1.5\n" +
              "Amount owed is 6.5\n" +
              "You earned 3 frequent renter points";
        assertEquals(result, rentalReportService.generateTextReport(CUSTOMER_NAME));
    }

    @Test
    void testMoviesWithExtraChargesAndAdditionalRenterPoints() {
        customer.getRentals().add(rentalRegular2);
        customer.getRentals().add(rentalNewRelease2);
        customer.getRentals().add(rentalChildren2);
        String result = "Rental Record for " + CUSTOMER_NAME + "\n" +
              "\t" + MOVIE_TITLE_REGULAR + "\t3.5\n" +
              "\t" + MOVIE_TITLE_NEW_RELEASE + "\t6.0\n" +
              "\t" + MOVIE_TITLE_CHILDREN + "\t3.0\n" +
              "Amount owed is 12.5\n" +
              "You earned 4 frequent renter points";
        assertEquals(result, rentalReportService.generateTextReport(CUSTOMER_NAME));
    }
}
