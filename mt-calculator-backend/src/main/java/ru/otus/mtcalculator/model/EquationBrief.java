package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class EquationBrief {
    @Column(name = "EquationId")
    private long equationId;
}
