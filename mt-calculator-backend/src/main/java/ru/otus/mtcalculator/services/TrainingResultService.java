package ru.otus.mtcalculator.services;

import ru.otus.mtcalculator.model.TrainingCheckResult;
import ru.otus.mtcalculator.model.TrainingSession;
import ru.otus.mtcalculator.model.TrainingSolution;

public interface TrainingResultService {
    TrainingCheckResult checkTrainingSolution(TrainingSession session, TrainingSolution solution);
}
