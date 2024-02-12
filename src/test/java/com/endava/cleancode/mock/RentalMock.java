package com.endava.cleancode.mock;

import com.endava.cleancode.model.Rental;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

import static com.endava.cleancode.mock.MovieMock.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentalMock {

    public static List<Rental> getMockedRentals() {
        return Stream.of(getMockedRentals1(), getMockedRentals2()).flatMap(List::stream).toList();
    }

    public static List<Rental> getMockedRentals1() {
        return List.of(getMockedRentalRegular1(), getMockedRentalNewRelease1(), getMockedRentalChildren1());
    }

    public static List<Rental> getMockedRentals2() {
        return List.of(getMockedRentalRegular2(), getMockedRentalNewRelease2(), getMockedRentalChildren2());
    }

    public static Rental getMockedRentalRegular1() {
        return Rental.builder().movie(getMockedRegularMovie1()).nrDaysRented(2).build();
    }

    public static Rental getMockedRentalNewRelease1() {
        return Rental.builder().movie(getMockedNewReleaseMovie1()).nrDaysRented(1).build();
    }

    public static Rental getMockedRentalChildren1() {
        return Rental.builder().movie(getMockedChildrenMovie1()).nrDaysRented(3).build();
    }

    public static Rental getMockedRentalRegular2() {
        return Rental.builder().movie(getMockedRegularMovie2()).nrDaysRented(3).build();
    }

    public static Rental getMockedRentalNewRelease2() {
        return Rental.builder().movie(getMockedNewReleaseMovie2()).nrDaysRented(2).build();
    }

    public static Rental getMockedRentalChildren2() {
        return Rental.builder().movie(getMockedChildrenMovie2()).nrDaysRented(4).build();
    }
}
