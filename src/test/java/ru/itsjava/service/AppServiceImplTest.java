package ru.itsjava.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Customer;
import ru.itsjava.services.*;

import java.util.List;

import static org.mockito.Mockito.when;

@DisplayName("Тест методов класса AppService")
@SpringBootTest
public class AppServiceImplTest {

    @Configuration
    static class AppServiceConfiguration{

        @Bean
        public IOService ioService(){
            IOServiceImpl mockIOService = Mockito.mock(IOServiceImpl.class);
            final int numMenu = 1;
            when(mockIOService.input()).thenReturn(String.valueOf(numMenu));
            return mockIOService;
        }

//        @Bean
//        public CustomerService customerService(){
//            CustomerServiceImpl mockCustomerService = Mockito.mock(CustomerServiceImpl.class);
//            when(customerService().findAll()).thenReturn(new CustomerService().findAll());
//        }
    }

    @Autowired
    private final CustomerService customerService;

    public AppServiceImplTest(CustomerService customerService) {
        this.customerService = customerService;
    }
//    private final IOService ioService;
//    private final PetDao petDao;

    @DisplayName("Тест метода printAllCustomers")
    @Test
    public void printAllCustomers(){

    }
}
