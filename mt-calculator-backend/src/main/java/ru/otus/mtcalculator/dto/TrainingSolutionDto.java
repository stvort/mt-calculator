package ru.otus.mtcalculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TrainingSolutionDto {

    @JsonProperty(value = "session_id")
    private String sessionId;

    @JsonProperty(value = "equation_results")
    private List<EquationResultDto> equationResults;
}
