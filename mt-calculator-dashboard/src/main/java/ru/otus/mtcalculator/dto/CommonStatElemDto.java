package ru.otus.mtcalculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonStatElemDto {
    @JsonProperty("Пользователь")
    private String userName;

    @JsonSerialize(nullsUsing = NumberSerializer.class)
    @JsonProperty("Счет последний")
    private Integer lastScore;

    @JsonSerialize(nullsUsing = NumberSerializer.class)
    @JsonProperty("Счет максимальный")
    private Integer maxScore;

    @JsonSerialize(nullsUsing = NumberSerializer.class)
    @JsonProperty("Счет средний за месяц")
    private Double avgScore;
}
