package ru.otus.mtcalculator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserStatElemDto {

    @JsonProperty("Дата")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @JsonSerialize(nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Длительность прохождения")
    private Integer duration;

    @JsonSerialize(nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Количество правильных ответов")
    private Long rightAnswersCount;

    @JsonSerialize(nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Всего вопросов")
    private Long totalQuestionCount;

    @JsonSerialize(nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Счет")
    private Integer score;
}
