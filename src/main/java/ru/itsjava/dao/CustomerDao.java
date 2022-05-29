package ru.itsjava.dao;

import ru.itsjava.domain.Customer;

import java.util.List;

public interface CustomerDao {

    int count();

    long insert(Customer customer);

    void updateCustomer(Customer customer);

    void delete(Customer customer);

    Customer findById(long id);

    List<Customer> findAll();
}
