package com.endava.cleancode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private static final String MOVIE_TITLE_REGULAR = "Regular Movie";
    private static final String MOVIE_TITLE_NEW_RELEASE = "New Release Movie";
    private static final String MOVIE_TITLE_CHILDREN = "Children Movie";
    private static final String CUSTOMER_NAME = "Customer";

    private static final Customer customer = new Customer(CUSTOMER_NAME);
    private static final Movie regularMovie = new Movie(MOVIE_TITLE_REGULAR, Movie.REGULAR);
    private static final Movie newReleaseMovie = new Movie(MOVIE_TITLE_NEW_RELEASE, Movie.NEW_RELEASE);
    private static final Movie childrenMovie = new Movie(MOVIE_TITLE_CHILDREN, Movie.CHILDREN);
    private static final Rental regularRental1 = new Rental(regularMovie, 2);
    private static final Rental newReleaseRental1 = new Rental(newReleaseMovie, 1);
    private static final Rental childrenRental1 = new Rental(childrenMovie, 3);
    private static final Rental regularRental2 = new Rental(regularMovie, 3);
    private static final Rental newReleaseRental2 = new Rental(newReleaseMovie, 2);
    private static final Rental childrenRental2 = new Rental(childrenMovie, 4);

    @BeforeEach
    void setUp() {
        customer.getRentals().clear();
    }

    @Test
    void testMoviesWithNoExtraChargesAndNoAdditionalRenterPoints() {
        customer.getRentals().add(regularRental1);
        customer.getRentals().add(newReleaseRental1);
        customer.getRentals().add(childrenRental1);
        String result = "Rental Record for " + CUSTOMER_NAME + "\n" +
                "\t" + MOVIE_TITLE_REGULAR + "\t2.0\n" +
                "\t" + MOVIE_TITLE_NEW_RELEASE + "\t3.0\n" +
                "\t" + MOVIE_TITLE_CHILDREN + "\t1.5\n" +
                "Amount owned is 6.5\n" +
                "You earned 3 frequent renter points";
        assertEquals(result, customer.statement());
    }

    @Test
    void testMoviesWithExtraChargesAndAdditionalRenterPoints() {
        customer.getRentals().add(regularRental2);
        customer.getRentals().add(newReleaseRental2);
        customer.getRentals().add(childrenRental2);
        String result = "Rental Record for " + CUSTOMER_NAME + "\n" +
                "\t" + MOVIE_TITLE_REGULAR + "\t3.5\n" +
                "\t" + MOVIE_TITLE_NEW_RELEASE + "\t6.0\n" +
                "\t" + MOVIE_TITLE_CHILDREN + "\t3.0\n" +
                "Amount owned is 12.5\n" +
                "You earned 4 frequent renter points";
        assertEquals(result, customer.statement());
    }
}
