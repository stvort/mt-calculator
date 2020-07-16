package ru.otus.mtcalculator.dto;

import lombok.Data;

@Data
public class EquationResultDto {
    private Long id;
    private Long result;
    private Boolean success;

}
