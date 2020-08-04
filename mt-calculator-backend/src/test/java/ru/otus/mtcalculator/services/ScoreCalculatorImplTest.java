package ru.otus.mtcalculator.services;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculatorImplTest {

    @Test
    void calcScore() {
        ScoreCalculator calculator = new ScoreCalculatorImpl();
        LocalDate birthDate = LocalDate.parse("1981-07-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int score = calculator.calcScore(10, 10, 3600, birthDate);
        System.out.println(score);
        assertThat(score).isGreaterThan(0);

    }
}