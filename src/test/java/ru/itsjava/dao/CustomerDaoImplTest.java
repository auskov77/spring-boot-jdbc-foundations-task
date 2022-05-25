package ru.itsjava.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(CustomerDaoImpl.class)
public class CustomerDaoImplTest {
    private static final String DEFAULT_NAME = "Запашный";
    private static final String DEFAULT_EMAIL= "zapashniy@mail.ru";
    private static final String DEFAULT_ANIMAL= "Tiger";
    private static final long FIRST_ID = 1L;
    private static final long NEW_ID = 3L;

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void shouldHaveCorrectCount() {
        int actualCustomerCount = customerDao.count();
        assertEquals(2, actualCustomerCount);
    }

    @Test
    public void shouldHaveCorrectMethodInsert() {
        Customer expectedCustomer = new Customer(NEW_ID,DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL);
        customerDao.insert(expectedCustomer);

        Customer customerDaoById = customerDao.findById(NEW_ID);

        assertEquals(customerDaoById, expectedCustomer);
    }

    @Test
    public void shouldHaveCorrectMethodUpdateCustomer() {
        Customer expectedCustomer = new Customer(FIRST_ID, DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL);
        customerDao.updateCustomer(expectedCustomer);

        Customer actualCustomer = customerDao.findById(FIRST_ID);
        assertEquals(actualCustomer, expectedCustomer);
    }

    @Test
    public void shouldHaveCorrectMethodDelete() {
        Customer deleteCustomer = customerDao.findById(FIRST_ID);
        customerDao.delete(deleteCustomer);

        assertEquals(customerDao.count(), 1);
    }

    @Test
    public void shouldHaveCorrectMethodFindById() {
        Customer expectedCustomer = customerDao.findById(FIRST_ID);

        assertEquals(expectedCustomer.getName(), "Ivanov");
        assertEquals(expectedCustomer.getEmail(), "ivanov1@yandex.ru");
        assertEquals(expectedCustomer.getAnimal(), "cat");
    }
}
