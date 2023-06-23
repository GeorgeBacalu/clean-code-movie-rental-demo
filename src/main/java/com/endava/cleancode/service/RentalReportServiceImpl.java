package com.endava.cleancode.service;

import com.endava.cleancode.model.Customer;
import com.endava.cleancode.model.Rental;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalReportServiceImpl implements RentalReportService {
    private final CustomerService customerService;

    @Override
    public String generateTextReport(String customerName) {
        Customer customer = customerService.getCustomerByName(customerName);
        StringBuilder result = new StringBuilder();
        result.append("Rental Record for ")
              .append(customer.getName())
              .append(":\n");
        customer.getRentals().forEach(rental ->
              result.append(rental.getMovie().getTitle())
                    .append(": ")
                    .append(customerService.getRentalPrice(rental))
                    .append("\n"));
        result.append("Amount owed: ")
              .append(customerService.getTotalPrice(customerName))
              .append("\nYou earned ")
              .append(customerService.getFrequentRenterPoints(customerName))
              .append(" frequent renter points");
        return result.toString();
    }

    @Override
    public String generateHtmlReport(String customerName) {
        Customer customer = customerService.getCustomerByName(customerName);
        StringBuilder result = new StringBuilder();
        String baseLine = """
              <!DOCTYPE html>
              <html lang="en">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <meta http-equiv="X-UA-Compatible" content="ie=edge">
                  <title>Movie Rental</title>
                </head>
                <body>
              """;
        List<Rental> rentals = customer.getRentals();
        result.append(baseLine).append("\t<ul>\n");
        rentals.forEach(rental ->
              result.append("\t  <li>")
                    .append(rental.getMovie().getTitle())
                    .append(": ")
                    .append(customerService.getRentalPrice(rental))
                    .append("</li>")
                    .append(System.lineSeparator()));
        result.append("\t</ul>\n")
              .append("\t<h3>Amount owed: ")
              .append(customerService.getTotalPrice(customerName))
              .append("</h3>\n")
              .append("\t<h3>You earned ")
              .append(customerService.getFrequentRenterPoints(customerName))
              .append(" frequent renter points</h3>");
        result.append("\n  </body>").append("\n</html>");
        return result.toString();
    }
}
