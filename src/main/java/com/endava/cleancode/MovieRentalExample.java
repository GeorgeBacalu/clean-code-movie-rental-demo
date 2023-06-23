package com.endava.cleancode;

import java.util.List;

public class MovieRentalExample {
    public static void main(String[] args) {
        Customer customer = new Customer("Customer", List.of(
              new Rental(new Movie("Movie1", Movie.REGULAR), 2),
              new Rental(new Movie("Movie2", Movie.NEW_RELEASE), 2),
              new Rental(new Movie("Movie3", Movie.CHILDREN), 3)));
        System.out.println(customer.statement());
    }
}
