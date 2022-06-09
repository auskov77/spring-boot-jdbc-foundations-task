package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itsjava.dao.CustomerDao;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест методов класса CustomerServiceImpl")
@SpringBootTest
class CustomerServiceImplTest {
    private static final String DEFAULT_NAME = "Запашный";
    private static final String DEFAULT_EMAIL = "zapashniy@mail.ru";
    private static final String DEFAULT_ANIMAL = "Tiger";
    public static final Pet DEFAULT_PET_FIRST_ID = new Pet(1L, "yard");

    @Autowired
    private CustomerDao customerDao;

    @DisplayName("Тест метода insert")
    @Test
    public void shouldHaveCorrectMethodInsert() {
        Customer expectedCustomer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL, DEFAULT_PET_FIRST_ID);
        long idExpectedCustomerFromDB = customerDao.insert(expectedCustomer);
        Customer customerById = customerDao.findById(idExpectedCustomerFromDB);

        assertAll(()->assertEquals(customerById.getName(), expectedCustomer.getName()),
                ()->assertEquals(customerById.getEmail(),expectedCustomer.getEmail()),
                ()->assertEquals(customerById.getAnimal(), expectedCustomer.getAnimal()));
    }

    @DisplayName("Тест метода findAll")
    @Test
    void shouldHaveCorrectMethodFindAll() {
        List<Customer> customerList = customerDao.findAll();

        assertEquals(3, customerList.size());
    }
}