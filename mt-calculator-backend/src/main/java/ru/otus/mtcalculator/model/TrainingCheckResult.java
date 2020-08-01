package ru.otus.mtcalculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
public class TrainingCheckResult {

    @JsonProperty(value = "session_id")
    private final String sessionId;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("client_time")
    private final Long duration;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("client_score")
    private Integer score;

    @JsonProperty(value = "client_result_answers")
    private final List<EquationResult> equationAnswersCheckResults = new ArrayList<>();
}
