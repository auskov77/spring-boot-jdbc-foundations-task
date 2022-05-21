package ru.itsjava.dao;

import ru.itsjava.domain.Customer;

public interface CustomerDao {

    int count();

    void insert(Customer customer);

    void updateCustomer(Customer customer);

    void delete(Customer customer);

    Customer findById(long id);
}
