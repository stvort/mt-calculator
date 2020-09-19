package ru.otus.mtcalculator.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Калькулятор очков должен")
class ScoreCalculatorImplTest {

    private static final String BIRTH_DATE_FOR_OLDMEN = "1981-07-23";
    public static final int TOTAL_EQUATIONS = 10;
    public static final int RIGHT_ANSWERS = 10;
    public static final int SESSION_DURATION_SEC = 10;

    @Test
    void shouldCorrectCalculateScore() {
        ScoreCalculator calculator = new ScoreCalculatorImpl();
        LocalDate birthDate = LocalDate.parse(BIRTH_DATE_FOR_OLDMEN, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int score = calculator.calcScore(TOTAL_EQUATIONS, RIGHT_ANSWERS, SESSION_DURATION_SEC, birthDate);
        assertThat(score).isGreaterThan(0);

    }
}