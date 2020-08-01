package ru.otus.mtcalculator.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.mtcalculator.model.*;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TrainingResultServiceImpl implements TrainingResultService {

    private final ScoreCalculator scoreCalculator;

    @Override
    public TrainingCheckResult checkTrainingSolution(TrainingSession session, TrainingSolution solution) {

        TrainingCheckResult result =
                new TrainingCheckResult(solution.getSessionId(), session.getDurationSec());

        int totalEquations = solution.getEquationResults().size();
        int rightAnswers = 0;

        Map<Long, Equation> equationMap = session.getEquations().stream()
                .collect(Collectors.toMap(Equation::getId, e -> e));

        for (EquationResult r : solution.getEquationResults()) {
            r.setSuccess(checkEquationAnswer(equationMap.get(r.getId()), r.getResult()));
            rightAnswers = r.getSuccess() ? rightAnswers + 1 : rightAnswers;
            result.getEquationAnswersCheckResults().add(r);
        }

        result.setScore(scoreCalculator.calcScore(totalEquations, rightAnswers,
                session.getDurationSec(), session.getUser().getBirthDate()));

        return result;
    }

    private boolean checkEquationAnswer(Equation e, long answer) {
        if (e == null) {
            return false;
        }

        long sol = e.getType().equalsIgnoreCase("m") ?
                e.getFirstNumber() * e.getSecondNumber() :
                e.getFirstNumber() / e.getSecondNumber();
        return sol == answer;
    }
}
