package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao{
    private final NamedParameterJdbcOperations jdbcOperations;

    @Override
    public List<Pet> findAll() {
        return jdbcOperations.query("select id, breed from pets", new PetMapper());
    }

    @Override
    public Pet findByBreed(String breed) {
        return jdbcOperations.queryForObject("select id, breed from pets where breed = :breed",
                new MapSqlParameterSource(Map.of("breed", breed)), new PetMapper());
    }

    private static class PetMapper implements RowMapper<Pet>{

        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException{
            return new Pet(rs.getLong("id"), rs.getString("breed"));
        }
    }
}
