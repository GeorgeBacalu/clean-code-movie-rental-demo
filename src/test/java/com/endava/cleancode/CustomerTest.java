package com.endava.cleancode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {
    private static final String MOVIE_TITLE_REGULAR = "Regular Movie";
    private static final String MOVIE_TITLE_NEW_RELEASE = "New Release Movie";
    private static final String MOVIE_TITLE_CHILDREN = "Children Movie";
    private static final String CUSTOMER_NAME = "Customer";

    private static final Customer customer = new Customer(CUSTOMER_NAME);
    private static final Movie movieRegular = new Movie(MOVIE_TITLE_REGULAR, Movie.REGULAR);
    private static final Movie movieNewRelease = new Movie(MOVIE_TITLE_NEW_RELEASE, Movie.NEW_RELEASE);
    private static final Movie movieChildren = new Movie(MOVIE_TITLE_CHILDREN, Movie.CHILDREN);
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
        assertEquals(result, customer.statement());
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
        assertEquals(result, customer.statement());
    }
}
