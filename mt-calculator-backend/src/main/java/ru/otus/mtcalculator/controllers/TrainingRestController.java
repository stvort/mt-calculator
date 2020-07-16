package ru.otus.mtcalculator.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.mtcalculator.dto.TrainingCheckResultDto;
import ru.otus.mtcalculator.dto.TrainingSolutionDto;

@CrossOrigin
@RestController
public class TrainingRestController {

    @PostMapping("/api/v1/end")
    public TrainingCheckResultDto checkSolution(@RequestBody TrainingSolutionDto solution) {
        System.out.println(solution);
        return new TrainingCheckResultDto();
    }

}
