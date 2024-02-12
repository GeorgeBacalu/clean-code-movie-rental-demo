package com.endava.cleancode.repository;

import com.endava.cleancode.model.Movie;
import com.endava.cleancode.model.Rental;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository {
    private final List<Rental> rentals = new ArrayList<>();
    private final MovieRepository movieRepository;

    @PostConstruct
    private void initializeRentals() {
        List<Movie> movies = movieRepository.findAll();
        rentals.add(new Rental(movies.get(0), 2));
        rentals.add(new Rental(movies.get(1), 1));
        rentals.add(new Rental(movies.get(2), 3));
        rentals.add(new Rental(movies.get(3), 3));
        rentals.add(new Rental(movies.get(4), 2));
        rentals.add(new Rental(movies.get(5), 4));
    }

    @Override
    public List<Rental> findAll() {
        return rentals;
    }
}
