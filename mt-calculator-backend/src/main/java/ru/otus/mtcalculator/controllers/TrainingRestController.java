package ru.otus.mtcalculator.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.otus.mtcalculator.config.JwtUser;
import ru.otus.mtcalculator.model.TrainingCheckResult;
import ru.otus.mtcalculator.model.Training;
import ru.otus.mtcalculator.model.TrainingSolution;
import ru.otus.mtcalculator.services.TrainingService;

@RequiredArgsConstructor
@CrossOrigin
@RestController
public class TrainingRestController {

    private final TrainingService trainingService;

    @GetMapping("/api/v1/start")
    public Training startTraining(Authentication a){
        JwtUser user = (JwtUser) a.getPrincipal();
        return trainingService.startTraining(user);
    }


    @PostMapping("/api/v1/end")
    public TrainingCheckResult checkSolution(@RequestBody TrainingSolution solution) {
        return trainingService.finishTraining(solution);
    }

}
