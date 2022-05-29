package ru.itsjava.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест методов класса PetDao")
@JdbcTest
@Import(PetDaoImpl.class)
public class PetDaoImplTest {

    public static final String FIRST_BREED = "yard";
    @Autowired
    private PetDao petDao;

    @DisplayName("Тест метода findAll")
    @Test
    public void shouldHaveCorrectMethodFindAll(){
        List<Pet> listPets = petDao.findAll();
        assertEquals(2, listPets.size());
    }

    @DisplayName("Тест метода findByBreed")
    @Test
    public void shouldHaveCorrectMethodFindBreed() {
        Pet expectedPet = petDao.findByBreed(FIRST_BREED);
        assertEquals(expectedPet.getBreed(), "yard");
    }
}
