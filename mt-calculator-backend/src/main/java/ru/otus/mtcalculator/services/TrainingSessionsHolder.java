package ru.otus.mtcalculator.services;

import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.Equation;
import ru.otus.mtcalculator.model.TrainingSession;

import java.util.List;
import java.util.Optional;

public interface TrainingSessionsHolder {
    String createSessionAndGetId(JwtUser user, List<Equation> equations);
    Optional<TrainingSession> getSession(String id);
    Optional<TrainingSession> finishSession(String id);
}
