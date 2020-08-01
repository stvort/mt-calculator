package ru.otus.mtcalculator.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserStatElem {

    @JsonProperty("Дата")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Длительность прохождения")
    private Integer duration;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Количество правильных ответов")
    private Long rightAnswersCount;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Всего вопросов")
    private Long totalQuestionCount;

    @JsonSerialize(using = ToStringSerializer.class, nullsUsing = NullNumberSerializer.class)
    @JsonProperty("Счет")
    private Integer score;
}
