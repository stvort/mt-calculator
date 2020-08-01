package ru.otus.mtcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@Embeddable
@Data
public class EquationBrief {
    @Column(name = "EquationId")
    private long equationId;
}
