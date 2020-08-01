package ru.otus.mtcalculator.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class User {
    @Column(name = "UserId")
    private long userId;
}
