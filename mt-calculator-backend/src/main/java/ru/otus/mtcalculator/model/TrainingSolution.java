package ru.otus.mtcalculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TrainingSolution {

    @JsonProperty(value = "session_id")
    private String sessionId;

    @JsonProperty(value = "equation_results")
    private List<EquationResult> equationResults;
}
