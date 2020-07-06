package ru.otus.mtcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.mtcalculator.dto.CommonStatElemDto;
import ru.otus.mtcalculator.dto.UserStatElemDto;
import ru.otus.mtcalculator.resopsitories.UsersScoreRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class MTCalculatorApplication {

	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext ctx = SpringApplication.run(MTCalculatorApplication.class, args);
	}

}
