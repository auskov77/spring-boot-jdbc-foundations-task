package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Тест методов класса AppService")
@SpringBootTest
public class AppServiceImplTest {
    private static final String DEFAULT_NAME = "Запашный";
    private static final String DEFAULT_EMAIL = "zapashniy@mail.ru";
    private static final String DEFAULT_ANIMAL = "Tiger";
    public static final Pet DEFAULT_PET = new Pet(1L, "yard");
    private static final Customer DEFAULT_CUSTOMER = new Customer(1L, DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL, DEFAULT_PET);

    @Configuration
    static class ConfigurationAppServiceImpl {
        public static final String PRIVET = "1";

        @Bean
        public CustomerService customerService() {
            CustomerService mockCustomerService = Mockito.mock(CustomerService.class);
            when(mockCustomerService.findAll()).thenReturn(List.of(DEFAULT_CUSTOMER));
            return mockCustomerService;
        }

        @Bean
        public IOService ioService() {
            IOService mockIOService = Mockito.mock(IOService.class);
            when(mockIOService.input()).thenReturn(PRIVET);
            return mockIOService;
        }

        @Bean
        public PetDao petDao() {
            PetDao mockPetDao = Mockito.mock(PetDao.class);
            when(mockPetDao.findAll()).thenReturn(List.of(DEFAULT_PET));
            return mockPetDao;
        }

        @Bean
        public AppService appService(IOService ioService, CustomerService customerService, PetDao petDao) {
            return new AppServiceImpl(customerService, ioService, petDao);
        }
    }

    @Autowired
    private AppService appService;

//    @DisplayName("Тест метода start")
//    @Test
//    void shouldHaveCorrectMethodStart() {
//
//    }

    @DisplayName("Тест метода printAllCustomers")
    @Test
    void shouldHaveCorrectMethodPrintAllCustomers() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        appService.printAllCustomers();

        assertEquals("Список всех владельцев животных [Customer(id=1, name=Запашный, email=zapashniy@mail.ru, animal=Tiger, pet=Pet(id=1, breed=yard))]\r\n",
                outputStream.toString());
    }

    @DisplayName("Тест метода insertCustomer")
    @Test
    public void shouldHaveCorrectMethodInsertCustomer() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        appService.insertCustomer();

        assertEquals("Введите владельца\r\n" +
                "Введите ФИО\r\n" +
                "Введите электронный адрес\r\n" +
                "Введите животное\r\n" +
                "Введите породу животного\r\n", outputStream.toString());
    }
}
