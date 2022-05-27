package ru.itsjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.domain.Customer;
import ru.itsjava.domain.Pet;
import ru.itsjava.services.CustomerService;

@SpringBootApplication
public class SpringBootJdbcFoundationsTaskApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootJdbcFoundationsTaskApplication.class, args);
		context.getBean(CustomerService.class).insert(new Customer("Uskova", "uskova@yandex.ru", "dog", new Pet(4L, "mini")));

//		Console.main(args);
	}

}
