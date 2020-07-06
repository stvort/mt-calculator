package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Equation {
    private long id;
}
