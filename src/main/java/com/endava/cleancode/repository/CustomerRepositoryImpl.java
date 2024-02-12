package com.endava.cleancode.repository;

import com.endava.cleancode.exception.ResourceNotFoundException;
import com.endava.cleancode.model.Customer;
import com.endava.cleancode.model.Rental;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();
    private final RentalRepository rentalRepository;

    @PostConstruct
    private void initializeCustomers() {
        List<Rental> rentals = rentalRepository.findAll();
        customers.add(new Customer("Customer1", rentals.subList(0, 3)));
        customers.add(new Customer("Customer2", rentals.subList(3, 6)));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return customers.stream().filter(customer -> customer.getName().equals(name)).findFirst();
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        int index = customers.indexOf(customer);
        if (index >= 0) {
            customers.set(index, customer);
        } else {
            customers.add(customer);
        }
        return customer;
    }

    @Override
    public void delete(Customer customer) {
        boolean customerExists = customers.remove(customer);
        if (!customerExists) {
            throw new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND, customer.getName()));
        }
    }

    @Override
    public void deleteAll() {
        customers.clear();
    }
}
