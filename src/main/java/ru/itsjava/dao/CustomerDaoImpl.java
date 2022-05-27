package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

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
    public long insert(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of("name", customer.getName(), "email", customer.getEmail(), "animal", customer.getAnimal(),
                "pet_id", customer.getPet().getId());
        jdbcOperations.update("INSERT INTO customers(name, email, animal, pet_id) VALUES (:name, :email, :animal, :pet_id)",
                new MapSqlParameterSource(params), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public void updateCustomer(Customer customer) {
        Map<String, Object> params = Map.of("id", customer.getId(), "name", customer.getName(), "email", customer.getEmail(), "animal", customer.getAnimal());
        jdbcOperations.update("update customers c set c.name = :name, c.email = :email, c.animal = :animal where c.id = :id", params);
    }

    @Override
    public void delete(Customer customer) {
        Map<String, Object> params = Map.of("id", customer.getId());
        jdbcOperations.update("delete from customers c where c.id = :id", params);
    }

    @Override
    public Customer findById(long id) {
        Map<String, Object> params = Map.of("id", id);
        return jdbcOperations.queryForObject("select c.id, c.name, c.email, c.animal, p.id, p.breed from customers c, pets p where c.id = :id " +
                "and c.pet_id = p.id", params, new CustomerMapper());
    }

    private static class CustomerMapper implements RowMapper<Customer>{

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customer(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("animal"),
                    new Pet(rs.getLong("id"), rs.getString("breed")));
        }
    }
}
