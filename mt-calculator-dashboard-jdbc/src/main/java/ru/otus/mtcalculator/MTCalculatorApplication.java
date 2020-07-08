package ru.otus.mtcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class MTCalculatorApplication {

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext ctx = SpringApplication.run(MTCalculatorApplication.class, args);
	}

}
