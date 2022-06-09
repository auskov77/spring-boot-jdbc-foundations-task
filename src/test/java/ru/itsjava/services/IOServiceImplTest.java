package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.services.IOService;
import ru.itsjava.services.IOServiceImpl;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест методов класса IOServiceImpl")
@SpringBootTest
class IOServiceImplTest {

    @Configuration
    static class ConfigurationIOServiceImpl {
        public static final String PRIVET = "privet";
        private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(PRIVET.getBytes());

        @Bean
        public IOService ioService(){
            return new IOServiceImpl(byteArrayInputStream);
        }
    }

    @Autowired
    private IOService ioService;

    @DisplayName("корректная работа метода input")
    @Test
    public void shouldHaveCorrectMethodInput(){
        assertEquals(ConfigurationIOServiceImpl.PRIVET, ioService.input());
    }
}
