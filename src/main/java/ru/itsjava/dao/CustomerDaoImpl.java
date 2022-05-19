package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Customer;

@SuppressWarnings("ALL")
@Repository
@RequiredArgsConstructor
public class CustomerDaoImpl implements CustomerDao {
    private final JdbcOperations jdbcOperations;

    @Override
    public int count() {
        return jdbcOperations.queryForObject("SELECT count(*) FROM customers", Integer.class);
    }

    @Override
    public void insert(Customer customer) {
        jdbcOperations.update("INSERT INTO customers(name, email, animal) VALUES (?, ?, ?)", customer.getName(), customer.getEmail(), customer.getAnimal());
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcOperations.update("update customers set name = ? where id = ?", customer.getName(), customer.getId());
        jdbcOperations.update("update customers set email = ? where id = ?", customer.getEmail(), customer.getId());
        jdbcOperations.update("update customers set animal = ? where id = ?", customer.getAnimal(), customer.getId());
    }

    @Override
    public void delete(Customer customer) {
        jdbcOperations.update("delete from customers where id = ?", customer.getId());
    }
}
