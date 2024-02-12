package com.endava.cleancode.mock;

import com.endava.cleancode.enums.PriceCode;
import com.endava.cleancode.model.Movie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieMock {

    public static List<Movie> getMockedMovies() {
        return List.of(getMockedRegularMovie1(), getMockedNewReleaseMovie1(), getMockedChildrenMovie1(), getMockedRegularMovie2(), getMockedNewReleaseMovie2(), getMockedChildrenMovie2());
    }

    public static Movie getMockedRegularMovie1() {
        return Movie.builder().title("Regular Movie1").priceCode(PriceCode.REGULAR).build();
    }

    public static Movie getMockedNewReleaseMovie1() {
        return Movie.builder().title("New Release Movie1").priceCode(PriceCode.NEW_RELEASE).build();
    }

    public static Movie getMockedChildrenMovie1() {
        return Movie.builder().title("Children Movie1").priceCode(PriceCode.CHILDREN).build();
    }

    public static Movie getMockedRegularMovie2() {
        return Movie.builder().title("Regular Movie2").priceCode(PriceCode.REGULAR).build();
    }

    public static Movie getMockedNewReleaseMovie2() {
        return Movie.builder().title("New Release Movie2").priceCode(PriceCode.NEW_RELEASE).build();
    }

    public static Movie getMockedChildrenMovie2() {
        return Movie.builder().title("Children Movie2").priceCode(PriceCode.CHILDREN).build();
    }
}
