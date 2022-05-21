package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@SuppressWarnings("ALL")
@Repository
@RequiredArgsConstructor
public class CustomerDaoImpl implements CustomerDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public int count() {
        return jdbcOperations.getJdbcOperations().queryForObject("SELECT count(*) FROM customers", Integer.class);
    }

    @Override
    public void insert(Customer customer) {
        Map<String, Object> params = Map.of("name", customer.getName(), "email", customer.getEmail(), "animal", customer.getAnimal());
        jdbcOperations.update("INSERT INTO customers(name, email, animal) VALUES (:name, :email, :animal)", params);
    }

    @Override
    public void updateCustomer(Customer customer) {
        Map<String, Object> params = Map.of("id", customer.getId(), "name", customer.getName(), "email", customer.getEmail(), "animal", customer.getAnimal());
        jdbcOperations.update("update customers set name = :name where id = :id", params);
        jdbcOperations.update("update customers set email = :email where id = :id", params);
        jdbcOperations.update("update customers set animal = :animal where id = :id", params);
    }

    @Override
    public void delete(Customer customer) {
        Map<String, Object> params = Map.of("id", customer.getId());
        jdbcOperations.update("delete from customers where id = :id", params);
    }

    @Override
    public Customer findById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbcOperations.queryForObject("select id, name, email, animal from customers where id = :id",
                params, new CustomerMapper());
    }

    private static class CustomerMapper implements RowMapper<Customer>{

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("animal"));
        }
    }
}
