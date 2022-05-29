package ru.itsjava.services;

import ru.itsjava.domain.Customer;

import java.util.List;

public interface CustomerService {
    void insert(Customer customer);
    List<Customer> findAll();
}
