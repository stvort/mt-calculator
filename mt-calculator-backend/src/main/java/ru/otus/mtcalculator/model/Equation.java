package ru.otus.mtcalculator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class Equation {

    @Id
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "first_number")
    private long firstNumber;

    @JsonProperty(value = "second_number")
    private long secondNumber;

    @JsonProperty(value = "type")
    private String type;

}
