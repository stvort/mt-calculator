package ru.otus.mtcalculator.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.PI;

@Service
public class ScoreCalculatorImpl implements ScoreCalculator {

    private static final int EARTH_RADIUS = 6371;
    private static final double DAYS_IN_EAR = 365d;

    @Override
    public int calcScore(int totalEquations, int rightAnswers, long duration, LocalDate birthDate) {
        //((Количество правильно отвеченных вопросов / Количество заданных вопросов) * Радиус Земли * Пи) /
        // Потраченное время / (Количество дней с даты рождения до сейчас / 365)
        final long daysBetweenBirthAndNow = ChronoUnit.DAYS.between(birthDate, LocalDate.now());

        return (int) Math.round(((rightAnswers / (double) totalEquations) * EARTH_RADIUS * PI) /
                duration / (daysBetweenBirthAndNow / DAYS_IN_EAR));
    }

}
