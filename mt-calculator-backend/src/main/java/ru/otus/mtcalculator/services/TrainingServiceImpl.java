package ru.otus.mtcalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.*;
import ru.otus.mtcalculator.resopsitories.EquationsRepository;
import ru.otus.mtcalculator.resopsitories.UserResultRepository;
import ru.otus.mtcalculator.resopsitories.UserScoreRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingSessionsHolder sessionsHolder;
    private final EquationsRepository equationsRepository;
    private final UserResultRepository userResultRepository;
    private final UserScoreRepository userScoreRepository;
    private final TrainingResultService trainingResultService;

    @Override
    public Training startTraining(JwtUser user) {
        List<Equation> equations = equationsRepository.findRandomEquations(10);
        String sessionId = sessionsHolder.createSessionAndGetId(user, equations);

        return new Training(sessionId, equations);

    }

    @Override
    public TrainingCheckResult finishTraining(TrainingSolution solution) {
        Optional<TrainingSession> trainingSession = sessionsHolder.finishSession(solution.getSessionId());
        return trainingSession.map(session -> {
            TrainingCheckResult result = trainingResultService.checkTrainingSolution(session, solution);

            return result;
        }).orElse(null);
    }
}
