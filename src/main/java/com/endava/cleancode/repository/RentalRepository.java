package com.endava.cleancode.repository;

import com.endava.cleancode.model.Rental;

import java.util.List;

public interface RentalRepository {

    List<Rental> findAll();
}
