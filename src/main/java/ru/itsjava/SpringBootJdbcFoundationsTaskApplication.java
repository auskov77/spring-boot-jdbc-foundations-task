package ru.itsjava;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itsjava.services.AppService;

import java.sql.SQLException;

@SpringBootApplication
public class SpringBootJdbcFoundationsTaskApplication {

	public static void main(String[] args) throws SQLException {
//		SpringApplication.run(SpringBootJdbcFoundationsTaskApplication.class, args);
		ApplicationContext context = SpringApplication.run(SpringBootJdbcFoundationsTaskApplication.class, args);
		context.getBean(AppService.class).start();

//		Console.main(args);
	}

}
