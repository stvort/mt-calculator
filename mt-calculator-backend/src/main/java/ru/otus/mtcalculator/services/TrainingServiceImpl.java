package ru.otus.mtcalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.*;
import ru.otus.mtcalculator.resopsitories.EquationsRepository;
import ru.otus.mtcalculator.resopsitories.UserResultRepository;
import ru.otus.mtcalculator.resopsitories.UserScoreRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

            UserBrief userBrief = new UserBrief(session.getUser().getId());
            TrainingSessionBrief sessionBrief = new TrainingSessionBrief(session.getId());
            Date resultDate = new Date(session.getStopTime());

            TrainingCheckResult result = trainingResultService.checkTrainingSolution(session, solution);
            prepareAndSaveUserScore(userBrief, sessionBrief, result.getDuration(), result.getScore(), resultDate);
            prepareAndSaveUserResults(result.getEquationAnswersCheckResults(), userBrief, sessionBrief, resultDate);

            return result;
        }).orElse(null);
    }

    private void prepareAndSaveUserScore(UserBrief userBrief, TrainingSessionBrief sessionBrief,
                                         long duration, int score, Date resultDate){
        UserScore userScore = new UserScore(0L, userBrief, duration,
                sessionBrief, score, resultDate);
        userScoreRepository.save(userScore);
    }

    private void prepareAndSaveUserResults(List<EquationResult> answers, UserBrief userBrief,
                                           TrainingSessionBrief sessionBrief, Date resultDate){
        List<UserResult> userResultList = answers.stream()
                .map(e -> new UserResult(0L, userBrief, new EquationBrief(e.getId()),
                        sessionBrief, e.getSuccess(), resultDate))
                .collect(Collectors.toList());
        userResultRepository.saveAll(userResultList);
    }
}
