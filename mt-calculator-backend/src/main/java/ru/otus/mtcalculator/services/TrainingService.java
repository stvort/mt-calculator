package ru.otus.mtcalculator.services;

import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.TrainingCheckResult;
import ru.otus.mtcalculator.model.Training;
import ru.otus.mtcalculator.model.TrainingSolution;

public interface TrainingService {

    Training startTraining(JwtUser user);
    TrainingCheckResult finishTraining(TrainingSolution solution);
}
