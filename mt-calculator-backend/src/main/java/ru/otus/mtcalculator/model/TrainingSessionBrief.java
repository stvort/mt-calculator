package ru.otus.mtcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@Data
@Embeddable
public class TrainingSessionBrief {

    @Column(name = "Session")
    private String sessionId;
}
