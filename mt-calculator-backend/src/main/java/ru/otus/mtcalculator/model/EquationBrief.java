package ru.otus.mtcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class EquationBrief {
    @Column(name = "EquationId")
    private long equationId;
}
