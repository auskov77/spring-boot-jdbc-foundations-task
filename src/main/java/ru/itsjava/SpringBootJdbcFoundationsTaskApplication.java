package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.itsjava.dao.CustomerDao;
import ru.itsjava.domain.Customer;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcFoundationsTaskApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(SpringBootJdbcFoundationsTaskApplication.class, args);

		// произвести подсчет всех строк в таблице Customers - всех пользователей
		CustomerDao customerDao = context.getBean(CustomerDao.class);
		System.out.println("customerDao.count() = " + customerDao.count());

		// вставить нового пользователя в таблицу Customers
		Customer newCustomer = new Customer("Запашный", "zapashniy@mail.ru", "Tiger");
		customerDao.insert(newCustomer);
		System.out.println("customerDao.count() = " + customerDao.count());

		// переопределить поля в строке таблицы Customers
		Customer updatedCustomer = new Customer("Ivanova", "ivanova@mail.ru", "Lion");
		updatedCustomer.setId(1L);
		customerDao.updateCustomer(updatedCustomer);

		// удалить строку (пользователя) из таблицы Customers
		newCustomer.setId(5L);
		customerDao.delete(newCustomer);
		System.out.println("customerDao.count() = " + customerDao.count());

		Console.main(args);
	}

}
