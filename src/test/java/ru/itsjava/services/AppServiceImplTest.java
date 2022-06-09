package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itsjava.dao.CustomerDao;
import ru.itsjava.dao.PetDao;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест методов класса AppService")
@SpringBootTest
public class AppServiceImplTest {
    private static final String DEFAULT_NAME = "Запашный";
    private static final String DEFAULT_EMAIL = "zapashniy@mail.ru";
    private static final String DEFAULT_ANIMAL = "Tiger";
    public static final Pet DEFAULT_PET_FIRST_ID = new Pet(1L, "yard");

//    @Configuration
//    static class AppServiceConfiguration {
//
//        @Bean
//        public IOService ioService() {
//            IOServiceImpl mockIOService = Mockito.mock(IOServiceImpl.class);
//            final int numMenu = 1;
//            when(mockIOService.input()).thenReturn(String.valueOf(numMenu));
//            return mockIOService;
//        }
//
////        @Bean
////        public CustomerService customerService() {
////            CustomerServiceImpl mockCustomerService = Mockito.mock(CustomerServiceImpl.class);
////            when(mockCustomerService.insert()).thenReturn(new Customer());
////            return new CustomerServiceImpl();
////        }
////
////        @Bean
////        public PetDao petDao() {
////            return new PetDaoImpl();
////        }
//
////        @Bean
////        public AppService appService(CustomerService customerService, IOService ioService, PetDao petDao) {
////            return new AppServiceImpl(customerService, ioService, petDao);
////        }
//    }

    @Autowired
    private IOService ioService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private PetDao petDao;

//    @DisplayName("Тест метода start")
//    @Test
//    void start() {
//    }

    @DisplayName("Тест метода printAllCustomers")
    @Test
    void printAllCustomers() {
        List<Customer> expectedCustomerList = customerService.findAll();

        assertEquals(Arrays.toString(expectedCustomerList.toArray()),
                "[Customer(id=1, name=Ivanov, email=ivanov1@yandex.ru, animal=cat, pet=Pet(id=1, breed=yard)), Customer(id=2, name=Petrov, email=petrov11@yandex.ru, animal=dog, pet=Pet(id=2, breed=fighting)), Customer(id=3, name=Запашный, email=zapashniy@mail.ru, animal=Tiger, pet=Pet(id=1, breed=yard))]");
    }

    @DisplayName("Тест метода insertCustomer")
    @Test
    public void shouldHaveCorrectMethodInsertCustomer() {
        Customer expectedCustomer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL, DEFAULT_ANIMAL, DEFAULT_PET_FIRST_ID);
        long idExpectedCustomerFromDB = customerDao.insert(expectedCustomer);
        Customer customerById = customerDao.findById(idExpectedCustomerFromDB);

        assertAll(()->assertEquals(customerById.getName(), expectedCustomer.getName()),
                ()->assertEquals(customerById.getEmail(),expectedCustomer.getEmail()),
                ()->assertEquals(customerById.getAnimal(), expectedCustomer.getAnimal()));

    }
}
