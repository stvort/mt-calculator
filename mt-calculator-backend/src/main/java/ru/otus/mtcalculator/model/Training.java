package ru.otus.mtcalculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Training {

    @JsonProperty(value = "session_id")
    private String sessionId;

    @JsonProperty(value = "equations")
    private List<Equation> equations;
}
