package com.endava.cleancode.repository;

import com.endava.cleancode.model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.endava.cleancode.mock.MovieMock.getMockedMovies;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class MovieRepositoryImplTest {

    @Autowired
    private MovieRepositoryImpl movieRepository;

    private List<Movie> movies;

    @BeforeEach
    void setUp() {
        movies = getMockedMovies();
    }

    @Test
    void findAll_test() {
        then(movieRepository.findAll()).isEqualTo(movies);
    }
}
