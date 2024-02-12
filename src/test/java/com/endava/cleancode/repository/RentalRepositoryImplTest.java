package com.endava.cleancode.repository;

import com.endava.cleancode.model.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.endava.cleancode.mock.RentalMock.getMockedRentals;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
class RentalRepositoryImplTest {

    @Autowired
    private RentalRepositoryImpl rentalRepository;

    private List<Rental> rentals;

    @BeforeEach
    void setUp() {
        rentals = getMockedRentals();
    }

    @Test
    void findAll_test() {
        then(rentalRepository.findAll()).isEqualTo(rentals);
    }
}
