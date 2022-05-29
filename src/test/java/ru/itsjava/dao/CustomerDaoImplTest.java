package ru.itsjava.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест методов класса CustomerDao")
@JdbcTest
@Import(CustomerDaoImpl.class)
public class CustomerDaoImplTest {
    private static final String DEFAULT_NAME = "Запашный";
    private static final String DEFAULT_EMAIL = "zapashniy@mail.ru";
    private static final String DEFAULT_ANIMAL = "Tiger";
    private static final long FIRST_ID = 1L;
    private static final long SECOND_ID = 2L;
    public static final Pet DEFAULT_PET_FIRST_ID = new Pet(1L, "yard");
    public static final Pet DEFAULT_PET_SECOND_ID = new Pet(2L, "fighting");

    @Autowired
    private CustomerDao customerDao;

    @DisplayName("Тест метода count")
    @Test
    public void shouldHaveCorrectCount() {
        int actualCustomerCount = customerDao.count();
        assertEquals(2, actualCustomerCount);
    }

    @DisplayName("Тест метода insert")
    @Test
    public void shouldHaveCorrectMethodInsert() {
        Customer expectedCustomer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL, DEFAULT_PET_FIRST_ID);
        long idFromDB = customerDao.insert(expectedCustomer);
        System.out.println(idFromDB);
        Customer customerDaoById = customerDao.findById(idFromDB);

        assertAll(() -> assertEquals(customerDaoById.getName(), expectedCustomer.getName()),
                () -> assertEquals(customerDaoById.getEmail(), expectedCustomer.getEmail()),
                () -> assertEquals(customerDaoById.getAnimal(), expectedCustomer.getAnimal()));
    }

    @DisplayName("Тест метода updateCustomer")
    @Test
    public void shouldHaveCorrectMethodUpdateCustomer() {
        Customer expectedCustomer = new Customer(SECOND_ID, DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL, DEFAULT_PET_SECOND_ID);
        customerDao.updateCustomer(expectedCustomer);

        Customer actualCustomer = customerDao.findById(SECOND_ID);
        assertEquals(actualCustomer, expectedCustomer);
    }

    @DisplayName("Тест метода delete")
    @Test
    public void shouldHaveCorrectMethodDelete() {
        Customer deleteCustomer = customerDao.findById(FIRST_ID);
        customerDao.delete(deleteCustomer);

        assertEquals(customerDao.count(), 1);
    }

    @DisplayName("Тест метода findById")
    @Test
    public void shouldHaveCorrectMethodFindById() {
        Customer expectedCustomer = customerDao.findById(FIRST_ID);

        assertEquals(expectedCustomer.getName(), "Ivanov");
        assertEquals(expectedCustomer.getEmail(), "ivanov1@yandex.ru");
        assertEquals(expectedCustomer.getAnimal(), "cat");
        assertEquals(expectedCustomer.getPet(), DEFAULT_PET_FIRST_ID);
    }

    @DisplayName("Тест метода findAll")
    @Test
    public void shouldHaveCorrectMethodFindAll(){
        List<Customer> listCustomers = customerDao.findAll();
        assertEquals(2, listCustomers.size());
    }
}
