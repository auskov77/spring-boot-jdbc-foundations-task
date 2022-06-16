package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.dao.CustomerDao;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Тест методов класса CustomerServiceImpl")
@SpringBootTest
class CustomerServiceImplTest {
    private static final String DEFAULT_NAME = "Запашный";
    private static final String DEFAULT_EMAIL = "zapashniy@mail.ru";
    private static final String DEFAULT_ANIMAL = "Tiger";
    public static final Pet DEFAULT_PET = new Pet(1L, "yard");
    public static final Customer DEFAULT_CUSTOMER = new Customer(1L, DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL, DEFAULT_PET);

    @Configuration
    static class ConfigurationCustomerServiceImpl {

        @Bean
        public CustomerDao customerDao() {
            CustomerDao mockCustomerDao = Mockito.mock(CustomerDao.class);
            when(mockCustomerDao.findAll()).thenReturn(List.of(DEFAULT_CUSTOMER));
            return mockCustomerDao;
        }

        @Bean
        public CustomerService customerService(){
            return new CustomerServiceImpl(customerDao());
        }
    }

    @Autowired
    private CustomerService customerService;

    @DisplayName("Тест метода insert")
    @Test
    public void shouldHaveCorrectMethodInsert() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        customerService.insert(DEFAULT_CUSTOMER);

        assertEquals("ID нового владельца животного = " + DEFAULT_CUSTOMER.getId(), outputStream.toString());
    }

    @DisplayName("Тест метода findAll")
    @Test
    void shouldHaveCorrectMethodFindAll() {
        assertEquals(1, customerService.findAll().size());
    }
}