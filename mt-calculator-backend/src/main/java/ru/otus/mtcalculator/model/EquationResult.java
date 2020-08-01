package ru.otus.mtcalculator.model;

import lombok.Data;

@Data
public class EquationResult {
    private Long id;
    private Long result;
    private Boolean success;

}
