package com.endava.cleancode.model;

import com.endava.cleancode.enums.PriceCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String title;
    private PriceCode priceCode;
}
