package ru.otus.mtcalculator.services;

import java.time.LocalDate;

public interface ScoreCalculator {
    int calcScore(int totalEquations, int rightAnswers, long duration, LocalDate birthDate);
}
