package com.endava.cleancode.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;
    private List<Rental> rentals;

    public Customer(String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
    }
}