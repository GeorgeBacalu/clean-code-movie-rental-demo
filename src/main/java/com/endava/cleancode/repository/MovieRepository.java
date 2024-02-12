package com.endava.cleancode.repository;

import com.endava.cleancode.model.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> findAll();
}
