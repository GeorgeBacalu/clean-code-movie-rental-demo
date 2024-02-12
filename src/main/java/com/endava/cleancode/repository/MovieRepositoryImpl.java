package com.endava.cleancode.repository;

import com.endava.cleancode.enums.PriceCode;
import com.endava.cleancode.model.Movie;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    private final List<Movie> movies = new ArrayList<>();

    @PostConstruct
    private void initializeMovies() {
        movies.add(new Movie("Regular Movie1", PriceCode.REGULAR));
        movies.add(new Movie("New Release Movie1", PriceCode.NEW_RELEASE));
        movies.add(new Movie("Children Movie1", PriceCode.CHILDREN));
        movies.add(new Movie("Regular Movie2", PriceCode.REGULAR));
        movies.add(new Movie("New Release Movie2", PriceCode.NEW_RELEASE));
        movies.add(new Movie("Children Movie2", PriceCode.CHILDREN));
    }

    @Override
    public List<Movie> findAll() {
        return movies;
    }
}
