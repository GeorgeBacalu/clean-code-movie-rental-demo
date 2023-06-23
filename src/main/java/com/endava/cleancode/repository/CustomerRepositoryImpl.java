package com.endava.cleancode.repository;

import com.endava.cleancode.exception.ResourceNotFoundException;
import com.endava.cleancode.model.Customer;
import com.endava.cleancode.model.Rental;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.endava.cleancode.constants.Constants.CUSTOMER_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();
    private final RentalRepository rentalRepository;

    @PostConstruct
    private void initializeCustomers() {
        List<Rental> rentals = rentalRepository.findAll();
        customers.add(new Customer("Customer1", List.of(rentals.get(0), rentals.get(1), rentals.get(2))));
        customers.add(new Customer("Customer2", List.of(rentals.get(3), rentals.get(4), rentals.get(5))));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return customers.stream().filter(customer -> name.equals(customer.getName())).findFirst();
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        int index = customers.indexOf(customer);
        if(index >= 0) {
            customers.set(index, customer);
        } else {
            customers.add(customer);
        }
        return customer;
    }

    @Override
    public void delete(Customer customer) {
        boolean isRemoved = customers.remove(customer);
        if (!isRemoved) {
            throw new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND, customer.getName()));
        }
    }

    @Override
    public void deleteAll() {
        customers.clear();
    }
}
