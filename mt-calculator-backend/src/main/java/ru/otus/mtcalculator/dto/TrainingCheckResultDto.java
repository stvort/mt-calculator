package ru.otus.mtcalculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;

public class TrainingCheckResultDto {

    @JsonProperty(value = "session_id")
    private String sessionId;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("client_time")
    private Integer duration;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("client_score")
    private Integer score;

    @JsonProperty(value = "client_result_answers")
    private List<EquationResultDto> equationAnswersCheckResults;
}
