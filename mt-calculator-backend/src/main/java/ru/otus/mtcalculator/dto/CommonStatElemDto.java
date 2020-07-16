package ru.otus.mtcalculator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonStatElemDto {
    @JsonProperty("Пользователь")
    private String userName;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Счет последний")
    private Integer lastScore;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Счет максимальный")
    private Integer maxScore;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Счет средний за месяц")
    private Double avgScore;
}
