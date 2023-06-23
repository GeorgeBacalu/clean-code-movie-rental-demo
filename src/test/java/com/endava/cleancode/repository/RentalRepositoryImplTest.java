package com.endava.cleancode.repository;

import com.endava.cleancode.model.Rental;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.endava.cleancode.mock.RentalMock.getMockedRentals;
import static org.assertj.core.api.Assertions.assertThat;

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
    void findAll_shouldReturnAllRentals() {
        assertThat(rentalRepository.findAll()).isEqualTo(rentals);
    }
}
