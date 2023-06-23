package com.endava.cleancode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private Movie movie;
    private Integer nrDaysRented;
}
